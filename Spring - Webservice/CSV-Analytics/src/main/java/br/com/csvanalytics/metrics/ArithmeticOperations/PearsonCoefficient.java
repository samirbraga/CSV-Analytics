package br.com.csvanalytics.metrics.ArithmeticOperations;

import java.util.List;

/**
 * The covariance divides by the product of the standard deviations
 */
public class PearsonCoefficient extends ArithmeticOperation {

    public PearsonCoefficient(List<Double> list, List<Double> optList) {
        super(list, optList);
    }

    @Override
    public double calculate() {
        List<Double> list = this.getList();
        List<Double> optList = this.getOptList();

        double stdList = new StandardDeviation(list).calculate();
        double stdOptList = new StandardDeviation(optList).calculate();
        double covariance = new Covariance(list, optList).calculate();

        return covariance / (stdList * stdOptList);
    }
}