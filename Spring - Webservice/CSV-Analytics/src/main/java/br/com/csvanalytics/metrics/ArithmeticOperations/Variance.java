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

        ArithmeticOperation mean = new Mean(this.getList());

        double x = mean.calculate();
        double variance = 0;

        for (double i: this.getList()) {
            variance =  variance + (i-x)*(i-x);
        }

        variance = variance/(this.getList().size()-1);
        
        return variance;
    }
}
