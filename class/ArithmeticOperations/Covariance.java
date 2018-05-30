import java.util.List;

public class Covariance extends ArithmeticOperation<Double> {

    public Covariance(List<Double> list, List<Double> optList) {
        super(list, optList);
    }

    @Override
    public Double calculate() {
        Mean mean = new Mean();

        double listMean = mean.calculate(list);
        double optlistMean = mean.calculate(optList);
        double covSum = 0.0;
        double covariance;

        for (int i = 0; i < list.size(); i++) {
            covSum += (list.get(i) - listMean) * (optList.get(i) - optListMean);
        }

        covariance = covSum / (list.size() -  1);


        return covariance;
    }
}