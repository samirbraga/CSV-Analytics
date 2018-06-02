package br.com.csvanalytics.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.csvanalytics.metrics.ArithmeticOperations.*;
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