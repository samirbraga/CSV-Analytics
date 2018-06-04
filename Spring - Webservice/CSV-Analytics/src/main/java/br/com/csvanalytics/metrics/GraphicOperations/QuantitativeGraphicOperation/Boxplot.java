package br.com.csvanalytics.metrics.GraphicOperations.QuantitativeGraphicOperation;

import br.com.csvanalytics.metrics.ArithmeticOperations.Min;
import br.com.csvanalytics.metrics.ArithmeticOperations.Max;
import br.com.csvanalytics.metrics.ArithmeticOperations.Median;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;

public class Boxplot extends QuantitativeGraphicOperation {

    public Boxplot(String[] labels, List<Map> data) {
		super(labels, data);
    }
    
    @Override
    public Object calculate() {
        String[] header = (String[]) this.getLabels();
        List<Map> csv = this.getData();

        List<Double> column = new ArrayList<Double>();

        for (Map row : csv) {
            column.add((Double) row.get(header[0]));
        }

        column.sort(Comparator.naturalOrder());
        int n = column.size();

        double min = new Min(column).calculate();
        double max = new Max(column).calculate();
        double median = new Median(column).calculate();
        double firstQuartile, thirdQuartile;

        if ((n & 1) == 1) {
            firstQuartile = new Median(column.subList(0, (n >> 1) - 1)).calculate();
            thirdQuartile = new Median(column.subList((n >> 1) + 1, n - 1)).calculate();
        } else {
            firstQuartile = new Median(column.subList(0, (n >> 1) - 1)).calculate();
            thirdQuartile = new Median(column.subList((n >> 1), n - 1)).calculate();
        }

        Double[] boxplot = new Double[]{min, firstQuartile, median, thirdQuartile, max};

        return boxplot;
    }
}