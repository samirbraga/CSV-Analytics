package br.com.csvanalytics.metrics.ArithmeticOperations;

import java.util.Collections;
import java.util.List;

/**
 * The numeric value separating the higher half of the ordered sample data from the lower half;
 * if n is odd the median is the center value.  If n is even the median is the average of the 2 center values
 */
public class Median extends ArithmeticOperation {

    public Median(List<Double> list) {
        super(list);
    }

    @Override
    public double calculate() {
        List<Double> list = this.getList();
        Collections.sort(list, null);

        double median;
        int n = list.size();

        if (n % 2 == 0) {
            median = (list.get(n / 2) + list.get((n / 2) - 1)) / 2;
        } else {
            median = list.get((Integer) n / 2);
        }

        return median;
    }
}
