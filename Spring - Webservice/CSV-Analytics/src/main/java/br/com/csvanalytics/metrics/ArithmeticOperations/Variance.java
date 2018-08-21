package br.com.csvanalytics.metrics.ArithmeticOperations;

import java.util.List;

/**
 * The sum of the squared differences between data values and the mean, divided by the count - 1
 */
public class Variance extends ArithmeticOperation {

    public Variance(List<Double> list) {
        super(list);
    }

    @Override
    public double calculate() {
        List<Double> list = this.getList();
        int n = list.size();
        
        double sum = SumOfPowers.calculate(list, 2);
        double variance = sum / (n - 1);
        return variance;
    }
}
