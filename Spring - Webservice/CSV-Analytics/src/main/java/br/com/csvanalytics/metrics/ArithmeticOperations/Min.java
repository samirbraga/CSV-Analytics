package br.com.csvanalytics.metrics.ArithmeticOperations;

import java.util.List;

/**
 * The smallest value in a sample data set
 */
public class Min extends ArithmeticOperation {

    public Min(List<Double> list) {
        super(list);
    }

    @Override
    public double calculate() {
        List<Double> list = this.getList();

        double min = list.get(0);

        for(Double value : list) {
            if(value < min)
                min = value;
        }

        return min;
    }
}