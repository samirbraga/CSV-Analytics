package br.com.csvanalytics.metrics.ArithmeticOperations;

import java.util.List;

/**
 * The sum of the products of differences between data values and their respective means, divided by the count minus 1
 */
public class Covariance extends ArithmeticOperation {

    public Covariance(List<Double> list, List<Double> optList) {
        super(list, optList);
    }

    @Override
    public double calculate() {
        List<Double> list = this.getList();
        List<Double> optList = this.getOptList();

        double listMean = new Mean(list).calculate();
        double optListMean = new Mean(optList).calculate();
        double productOfDiffOfSum = 0.0;
        int n = list.size();

        for (int i = 0; i < n; i++) {
            productOfDiffOfSum += (list.get(i) - listMean) * (optList.get(i) - optListMean);
        }

        double covariance = productOfDiffOfSum / (n - 1);
        return covariance;
    }
}