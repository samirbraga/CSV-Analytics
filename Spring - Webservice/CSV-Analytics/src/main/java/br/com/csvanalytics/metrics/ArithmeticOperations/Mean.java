package br.com.csvanalytics.metrics.ArithmeticOperations;

import java.util.List;

/**
 * The sum of all of the data divided by the count; the average
 */
public class Mean extends ArithmeticOperation {

    public Mean(List<Double> list) {
        super(list);
    }
    
    @Override
    public double calculate() {

        double n = 0;

        for(Double i : this.getList()) {
            n = i + n;
        }

        return (n/(this.getList()).size());
    }
}
