package br.com.csvanalytics.metrics.GraphicOperations.QuantitativeGraphicOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import br.com.csvanalytics.metrics.ArithmeticOperations.Max;
import br.com.csvanalytics.metrics.ArithmeticOperations.Median;
import br.com.csvanalytics.metrics.ArithmeticOperations.Min;

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
            column.add((Double)Double.parseDouble((String)row.get(header[0])));
        }

        column.sort(Comparator.naturalOrder());
        int n = column.size();

        double min = new Min(column).calculate();
        double max = new Max(column).calculate();
        double median = new Median(column).calculate();
        double firstQuartile, thirdQuartile;

        if ((n % 2) == 1) {
            firstQuartile = new Median(column.subList(0, (n / 2) - 1)).calculate();
            thirdQuartile = new Median(column.subList((n / 2) + 1, n - 1)).calculate();
        } else {
            firstQuartile = new Median(column.subList(0, (n / 2) - 1)).calculate();
            thirdQuartile = new Median(column.subList((n / 2), n - 1)).calculate();
        }

        List<Double> boxplot = Arrays.asList(new Double[]{min, firstQuartile, median, thirdQuartile, max});

        return boxplot;
    }
}