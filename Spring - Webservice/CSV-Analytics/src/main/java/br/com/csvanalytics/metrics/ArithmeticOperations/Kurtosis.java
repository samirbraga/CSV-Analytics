package br.com.csvanalytics.metrics.ArithmeticOperations;

import java.util.List;

/**
 * The sum of the fourth power of differences between data values and the mean, 
 * divided by the count minus 1 times the fourth power of the standard deviation;
 */
public class Kurtosis extends ArithmeticOperation {

    public Kurtosis(List<Double> list) {
        super(list);
    }

    @Override
    public double calculate() {
        List<Double> list = this.getList();

        double standardDeviation = new StandardDeviation(list).calculate();
        double sum = SumOfPowers.calculate(list, 4);
        int n = list.size(); 

        return sum / ((n - 1) * Math.pow(standardDeviation, 4));
    }
}