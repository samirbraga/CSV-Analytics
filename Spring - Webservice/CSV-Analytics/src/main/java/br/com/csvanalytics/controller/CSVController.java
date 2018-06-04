package br.com.csvanalytics.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.csvanalytics.metrics.ArithmeticOperations.Covariance;
import br.com.csvanalytics.metrics.ArithmeticOperations.Kurtosis;
import br.com.csvanalytics.metrics.ArithmeticOperations.Max;
import br.com.csvanalytics.metrics.ArithmeticOperations.Mean;
import br.com.csvanalytics.metrics.ArithmeticOperations.Median;
import br.com.csvanalytics.metrics.ArithmeticOperations.Min;
import br.com.csvanalytics.metrics.ArithmeticOperations.Mode;
import br.com.csvanalytics.metrics.ArithmeticOperations.PearsonCoefficient;
import br.com.csvanalytics.metrics.ArithmeticOperations.Skewness;
import br.com.csvanalytics.metrics.ArithmeticOperations.StandardDeviation;
import br.com.csvanalytics.metrics.ArithmeticOperations.Variance;
import br.com.csvanalytics.metrics.GraphicOperations.ContingencyTable;
import br.com.csvanalytics.metrics.GraphicOperations.FrequencyTable;
import br.com.csvanalytics.metrics.GraphicOperations.QuantitativeGraphicOperation.Histogram;
import br.com.csvanalytics.metrics.GraphicOperations.QuantitativeGraphicOperation.QuantitativeFrequencyTable;
import br.com.csvanalytics.metrics.GraphicOperations.QuantitativeGraphicOperation.Scatterplot;
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

	public static Map<String, Double> modeCalculate(String token) {
		Map selectedSession = Session.getSession(token);

		Map mode = null;
		mode = (Map) selectedSession.get("mode");
		if (mode != null) {
			return mode;
		} else {
			mode = new HashMap<String, List>();
			String[] quantitatives = (String[]) selectedSession.get("quantitatives");

			for (String title : quantitatives) {
				String[] column = selectColumn(title, selectedSession);
				List<Double> columnData = convertColumnToDouble(column);

				Mode modeCalc = new Mode<Double>(columnData);

				mode.put(title, modeCalc.calculate());
			}

			String[] qualitatives = (String[]) selectedSession.get("qualitatives");

			for (String title : qualitatives) {
				String[] column = selectColumn(title, selectedSession);
				List<String> columnData = Arrays.asList(column);

				Mode modeCalc = new Mode<String>(columnData);

				mode.put(title, modeCalc.calculate());
			}

			Session.updateSession(token, "mode", mode);

			return mode;
		}
	}

	public static Map covarianceCalculate(String token) {
		Map selectedSession = Session.getSession(token);
		String[] quantitatives = (String[]) selectedSession.get("quantitatives");

		if (quantitatives.length >= 2) {
			Map covariance = null;
			covariance = (Map) selectedSession.get("covariance");
			if (covariance != null) {
				return covariance;
			} else {
				covariance = new HashMap<String, Map>();

				for (String title : quantitatives) {
					for (String title2 : quantitatives) {
						if (!title.equals(title2) && covariance.get(title + ", " + title2) == null && covariance.get(title2 + ", " + title) == null) {
							String[] column = selectColumn(title, selectedSession);
							List<Double> columnData = convertColumnToDouble(column);
							String[] column2 = selectColumn(title2, selectedSession);
							List<Double> columnData2 = convertColumnToDouble(column);

							Covariance covarianceCalc = new Covariance(columnData, columnData2);

							covariance.put(title + ", " + title2, covarianceCalc.calculate());
						}
					}
				}

				Session.updateSession(token, "covariance", covariance);

				return covariance;
			}
		} else {
			Map response = new HashMap<String, String>();
			response.put("Cálculo inválido", "Nao pode ser calculado, pois há apenas um dado quantitativo");
			return response;
		}
	}

	public static Map pearsonCoefficientCalculate(String token) {
		Map selectedSession = Session.getSession(token);
		String[] quantitatives = (String[]) selectedSession.get("quantitatives");

		if (quantitatives.length >= 2) {
			Map pearsonCoefficient = null;
			pearsonCoefficient = (Map) selectedSession.get("pearsonCoefficient");
			if (pearsonCoefficient != null) {
				return pearsonCoefficient;
			} else {
				pearsonCoefficient = new HashMap<String, Map>();
	
				for (String title : quantitatives) {
					for (String title2 : quantitatives) {
						if (!title.equals(title2) && pearsonCoefficient.get(title + ", " + title2) == null && pearsonCoefficient.get(title2 + ", " + title) == null) {
							String[] column = selectColumn(title, selectedSession);
							List<Double> columnData = convertColumnToDouble(column);
							String[] column2 = selectColumn(title2, selectedSession);
							List<Double> columnData2 = convertColumnToDouble(column);
			
							PearsonCoefficient pearsonCoefficientCalc = new PearsonCoefficient(columnData, columnData2);
			
							pearsonCoefficient.put(title + ", " + title2, pearsonCoefficientCalc.calculate());
						}
					}
				}
	
				Session.updateSession(token, "pearsonCoefficient", pearsonCoefficient);
	
				return pearsonCoefficient;
			}
		} else {
			Map response = new HashMap<String, String>();
			response.put("Cálculo inválido", "Nao pode ser calculado, pois há apenas um dado quantitativo");
			return response;
		}
	}

	public static Map<String, Double> skewnessCalculate(String token) {
		Map selectedSession = Session.getSession(token);

		Map skewness = null;
		skewness = (Map) selectedSession.get("skewness");
		if (skewness != null) {
			return skewness;
		} else {
			skewness = new HashMap<String, Map>();
			String[] quantitatives = (String[]) selectedSession.get("quantitatives");

			for (String title : quantitatives) {
				String[] column = selectColumn(title, selectedSession);
				List<Double> columnData = convertColumnToDouble(column);

				Skewness skewnessCalc = new Skewness(columnData);

				skewness.put(title, skewnessCalc.calculate());
			}

			Session.updateSession(token, "skewness", skewness);

			return skewness;
		}
	}

	public static Map<String, Double> standardDeviationCalculate(String token) {
		Map selectedSession = Session.getSession(token);

		Map standardDeviation = null;
		standardDeviation = (Map) selectedSession.get("standardDeviation");
		if (standardDeviation != null) {
			return standardDeviation;
		} else {
			standardDeviation = new HashMap<String, Map>();
			String[] quantitatives = (String[]) selectedSession.get("quantitatives");

			for (String title : quantitatives) {
				String[] column = selectColumn(title, selectedSession);
				List<Double> columnData = convertColumnToDouble(column);

				StandardDeviation standardDeviationCalc = new StandardDeviation(columnData);

				standardDeviation.put(title, standardDeviationCalc.calculate());
			}

			Session.updateSession(token, "standardDeviation", standardDeviation);

			return standardDeviation;
		}
	}

	public static Map<String, Double> varianceCalculate(String token) {
		Map selectedSession = Session.getSession(token);

		Map variance = null;
		variance = (Map) selectedSession.get("variance");
		if (variance != null) {
			return variance;
		} else {
			variance = new HashMap<String, Map>();
			String[] quantitatives = (String[]) selectedSession.get("quantitatives");

			for (String title : quantitatives) {
				String[] column = selectColumn(title, selectedSession);
				List<Double> columnData = convertColumnToDouble(column);

				Variance varianceCalc = new Variance(columnData);

				variance.put(title, varianceCalc.calculate());
			}

			Session.updateSession(token, "variance", variance);

			return variance;
		}
	}

	public static Map contingencyTableCalculate(String token) {
		Map selectedSession = Session.getSession(token);

		Map contingencyTable = null;
		contingencyTable = (Map) selectedSession.get("contingencyTable");
		if (contingencyTable != null) {
			return contingencyTable;
		} else {
			contingencyTable = new HashMap<String, Map>();
			String[] qualitatives = (String[]) selectedSession.get("qualitatives");

			if (qualitatives.length > 1) {
				String[] header = (String[]) selectedSession.get("header");
				List<Map> records = Arrays.asList((Map[]) selectedSession.get("records"));

				for (String title : qualitatives) {
					String[] column = selectColumn(title, selectedSession);
					List<String> columnData = Arrays.asList(column);

					ContingencyTable contingencyTableCalc = new ContingencyTable(qualitatives, records, title);

					contingencyTable.put(title, contingencyTableCalc.calculate());
				}

				Session.updateSession(token, "contingencyTable", contingencyTable);
			} else {
				contingencyTable.put("status", "error");
				contingencyTable.put("Cálculo inválido", "Não pode ser calculado, pois há apenas um dado qualitativo");
			}

			return contingencyTable;
		}
	}

	public static Map quantitativeFrequencyTableCalculate(String token) {
		Map selectedSession = Session.getSession(token);

		Map frequencyTable = null;
		frequencyTable = (Map) selectedSession.get("quantitativeFrequencyTable");
		if (frequencyTable != null) {
			return frequencyTable;
		} else {
			frequencyTable = new HashMap<String, List>();
			String[] header = (String[]) selectedSession.get("header");
			List<Map> records = Arrays.asList((Map[]) selectedSession.get("records"));
			String[] quantitatives = (String[]) selectedSession.get("quantitatives");

			for (String title : quantitatives) {
				String[] column = selectColumn(title, selectedSession);
				List<String> columnData = Arrays.asList(column);

				QuantitativeFrequencyTable frequencyTableCalc = new QuantitativeFrequencyTable(new String[]{title}, records);

				frequencyTable.put(title, frequencyTableCalc.calculate());
			}

			Session.updateSession(token, "quantitativeFrequencyTable", frequencyTable);

			return frequencyTable;
		}
	}

	public static Map qualitativeFrequencyTableCalculate(String token) {
		Map selectedSession = Session.getSession(token);

		Map frequencyTable = null;
		frequencyTable = (Map) selectedSession.get("quanlitativeFrequencyTable");
		if (frequencyTable != null) {
			return frequencyTable;
		} else {
			frequencyTable = new HashMap<String, List>();
			String[] header = (String[]) selectedSession.get("header");
			List<Map> records = Arrays.asList((Map[]) selectedSession.get("records"));
			String[] qualitatives = (String[]) selectedSession.get("qualitatives");

			for (String title : qualitatives) {
				String[] column = selectColumn(title, selectedSession);
				List<String> columnData = Arrays.asList(column);

				FrequencyTable frequencyTableCalc = new FrequencyTable(new String[]{title}, records);

				frequencyTable.put(title, frequencyTableCalc.calculate());
			}

			Session.updateSession(token, "quanlitativeFrequencyTable", frequencyTable);

			return frequencyTable;
		}
	}

	public static Map scatterPlotCalculate(String token) {
		Map selectedSession = Session.getSession(token);

		Map scatterPlot = null;
		scatterPlot = (Map) selectedSession.get("scatterPlot");
		if (scatterPlot != null) {
			return scatterPlot;
		} else {
			scatterPlot = new HashMap<String, List>();
			List<Map> records = Arrays.asList((Map[]) selectedSession.get("records"));
			String[] quantitatives = (String[]) selectedSession.get("quantitatives");
			
			for (String title : quantitatives) {
				for (String title2 : quantitatives) {
					if (!title.equals(title2) && scatterPlot.get(quantitatives[0] + ", " + quantitatives[1]) == null && scatterPlot.get(quantitatives[1] + ", " + quantitatives[0]) == null) {
						String[] columns = { title, title2 };
						Scatterplot scatterPlotCalc = new Scatterplot(columns, records);
						scatterPlot.put(title + ", " + title2, scatterPlotCalc.calculate());
					}
				}
			}

			Session.updateSession(token, "scatterPlot", scatterPlot);

			return scatterPlot;
		}
	}

	public static Map histogramCalculate(String token) {
		Map selectedSession = Session.getSession(token);

		Map histogram = null;
		histogram = (Map) selectedSession.get("histogram");
		if (histogram != null) {
			return histogram;
		} else {
			histogram = new HashMap<String, List>();
			String[] header = (String[]) selectedSession.get("header");
			List<Map> records = Arrays.asList((Map[]) selectedSession.get("records"));
			String[] quantitatives = (String[]) selectedSession.get("quantitatives");

			for (String title : quantitatives) {
				String[] column = selectColumn(title, selectedSession);
				List<String> columnData = Arrays.asList(column);

				Histogram histogramCalc = new Histogram(quantitatives, records);

				histogram.put(title, histogramCalc.calculate());
			}

			Session.updateSession(token, "histogram", histogram);

			return histogram;
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