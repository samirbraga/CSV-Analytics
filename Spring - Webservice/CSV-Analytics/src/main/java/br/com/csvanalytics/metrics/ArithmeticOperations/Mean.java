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
        List<Double> list = this.getList();

        double sum = 0;
        int n = list.size();

        for(Double value : list) {
            sum += value;
        }
        
        double mean = sum / n;
        return mean;
    }
}
