import java.util.List;

public class Skewness extends ArithmeticOperation<Double> {

    public Skewness(List<Double> list) {
        super(list);
    }

    @Override
    public Double calculate() {
        Mean m = new Mean();
        double mean = m.calculate(list);

        StandardDeviation s = new StandardDeviation();
        double std = s.calculate(list);

        double cubeSum = 0;
        double skewness;

        for (double value : list) {
            cubeSum += Math.pow(value - mean, 3);
        }

        skewness = cubeSum / ((list.size() - 1) * Math.pow(std, 3))

        return skewness;
    }
}