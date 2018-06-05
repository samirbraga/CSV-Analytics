package br.com.csvanalytics.metrics.ArithmeticOperations;

import java.util.List;
import java.lang.Math;

/**
 * The square root of the variance
 */
public class StandardDeviation extends ArithmeticOperation {

    public StandardDeviation(List<Double> list) {
        super(list);
    }

    @Override
    public double calculate() {
        List<Double> list = this.getList();

        double variance = new Variance(list).calculate();

        double standardDeviation = Math.sqrt(variance);
        return standardDeviation;
    }
}
