package br.com.csvanalytics.metrics.ArithmeticOperations;

import java.util.List;

/**
 * The largest value in a sample data set
 */
public class Max extends ArithmeticOperation {

    public Max(List<Double> list) {
        super(list);
    }

    @Override
    public double calculate() {
        List<Double> list = this.getList();

        double max = list.get(0);

        for(Double value : list) {
            if(value > max)
              max = value;
        }

        return max;
    }
}