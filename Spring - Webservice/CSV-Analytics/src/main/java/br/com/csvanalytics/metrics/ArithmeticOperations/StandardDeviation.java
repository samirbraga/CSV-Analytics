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

        ArithmeticOperation standard = new Variance(this.getList());

        return Math.sqrt(standard.calculate());
    }
}
