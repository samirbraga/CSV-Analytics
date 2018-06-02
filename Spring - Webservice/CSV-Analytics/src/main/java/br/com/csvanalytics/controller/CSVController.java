package br.com.csvanalytics.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.csvanalytics.metrics.ArithmeticOperations.Kurtosis;
import br.com.csvanalytics.metrics.ArithmeticOperations.Max;
import br.com.csvanalytics.metrics.ArithmeticOperations.Mean;
import br.com.csvanalytics.metrics.ArithmeticOperations.Median;
import br.com.csvanalytics.metrics.ArithmeticOperations.Min;
import br.com.csvanalytics.metrics.GraphicOperations.ContingencyTable;
import br.com.csvanalytics.model.Session;

public class CSVController {
	public static Map<String, Double> averageCalculate(String token) {
		Map selectedSession = Session.getSession(token);

		Map average = null;
		average = (Map) selectedSession.get("average");
		if (average != null) {
			return average;
		} else {
			average = new HashMap<String, Map>();
			String[] quantitatives = (String[]) selectedSession.get("quantitatives");

			for (String title : quantitatives) {
				String[] column = selectColumn(title, selectedSession);
				List<Double> columnData = convertColumnToDouble(column);

				Mean averageCalc = new Mean(columnData);

				average.put(title, averageCalc.calculate());
			}

			Session.updateSession(token, "average", average);

			return average;
		}
	}

	public static Map<String, Double> minCalculate(String token) {
		Map selectedSession = Session.getSession(token);

		Map min = null;
		min = (Map) selectedSession.get("min");
		if (min != null) {
			return min;
		} else {
			min = new HashMap<String, Map>();
			String[] quantitatives = (String[]) selectedSession.get("quantitatives");

			for (String title : quantitatives) {
				String[] column = selectColumn(title, selectedSession);
				List<Double> columnData = convertColumnToDouble(column);

				Min minCalc = new Min(columnData);

				min.put(title, minCalc.calculate());
			}

			Session.updateSession(token, "min", min);

			return min;
		}
	}

	public static Map<String, Double> maxCalculate(String token) {
		Map selectedSession = Session.getSession(token);

		Map max = null;
		max = (Map) selectedSession.get("max");
		if (max != null) {
			return max;
		} else {
			max = new HashMap<String, Map>();
			String[] quantitatives = (String[]) selectedSession.get("quantitatives");

			for (String title : quantitatives) {
				String[] column = selectColumn(title, selectedSession);
				List<Double> columnData = convertColumnToDouble(column);

				Max maxCalc = new Max(columnData);

				max.put(title, maxCalc.calculate());
			}

			Session.updateSession(token, "max", max);

			return max;
		}
	}

	public static Map<String, Double> medianCalculate(String token) {
		Map selectedSession = Session.getSession(token);

		Map median = null;
		median = (Map) selectedSession.get("median");
		if (median != null) {
			return median;
		} else {
			median = new HashMap<String, Map>();
			String[] quantitatives = (String[]) selectedSession.get("quantitatives");

			for (String title : quantitatives) {
				String[] column = selectColumn(title, selectedSession);
				List<Double> columnData = convertColumnToDouble(column);

				Median medianCalc = new Median(columnData);

				median.put(title, medianCalc.calculate());
			}

			Session.updateSession(token, "median", median);

			return median;
		}
	}

	public static Map<String, Double> kurtosisCalculate(String token) {
		Map selectedSession = Session.getSession(token);

		Map kurtosis = null;
		kurtosis = (Map) selectedSession.get("kurtosis");
		if (kurtosis != null) {
			return kurtosis;
		} else {
			kurtosis = new HashMap<String, Map>();
			String[] quantitatives = (String[]) selectedSession.get("quantitatives");

			for (String title : quantitatives) {
				String[] column = selectColumn(title, selectedSession);
				List<Double> columnData = convertColumnToDouble(column);

				Kurtosis kurtosisCalc = new Kurtosis(columnData);

				kurtosis.put(title, kurtosisCalc.calculate());
			}

			Session.updateSession(token, "kurtosis", kurtosis);

			return kurtosis;
		}
	}

	// public static Map<String, Double> covarianceCalculate(String token) {
	// 	Map selectedSession = Session.getSession(token);

	// 	Map covariance = null;
	// 	covariance = (Map) selectedSession.get("covariance");
	// 	if (covariance != null) {
	// 		return covariance;
	// 	} else {
	// 		covariance = new HashMap<String, Map>();
	// 		String[] quantitatives = (String[]) selectedSession.get("quantitatives");

	// 		for (String title : quantitatives) {
	// 			String[] column = selectColumn(title, selectedSession);
	// 			List<Double> columnData = convertColumnToDouble(column);

	// 			Covariance covarianceCalc = new Covariance(columnData);

	// 			covariance.put(title, covarianceCalc.calculate());
	// 		}

	// 		Session.updateSession(token, "covariance", covariance);

	// 		return covariance;
	// 	}
	// }

	public static Map contingencyTableCalculate(String token) {
		Map selectedSession = Session.getSession(token);

		Map contingencyTable = null;
		contingencyTable = (Map) selectedSession.get("contingencyTable");
		if (contingencyTable != null) {
			return contingencyTable;
		} else {
			contingencyTable = new HashMap<String, Map>();
			String[] header = (String[]) selectedSession.get("header");
			List<Map> records = Arrays.asList((Map[]) selectedSession.get("records"));
			String[] qualitatives = (String[]) selectedSession.get("qualitatives");

			for (String title : qualitatives) {
				String[] column = selectColumn(title, selectedSession);
				List<String> columnData = Arrays.asList(column);

				ContingencyTable contingencyTableCalc = new ContingencyTable(qualitatives, records, title);

				contingencyTable.put(title, contingencyTableCalc.calculate());
			}

			Session.updateSession(token, "contingencyTable", contingencyTable);

			return contingencyTable;
		}
	}

	static String[] selectColumn(String title, Map<String, Object[]> session) {
		Map[] records = (Map[]) session.get("records");
		String[] column = new String[records.length];

		for (int i = 0; i < records.length; i++) {
			column[i] = (String) ((Map) records[i]).get(title);
		}

		return column;
	}

	static List<Double> convertColumnToDouble(String[] column) {
		List<Double> columnData = new ArrayList<Double>();

		for (int i = 0; i < column.length; i++) {
			columnData.add(Double.parseDouble(column[i]));
		}

		return columnData;
	}
}