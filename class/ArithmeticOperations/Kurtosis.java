import java.util.List;

/**
 * The sum of the fourth power of differences between data values and the mean, 
 * divided by the count minus 1 times the fourth power of the standard standard deviation;
 */
public class Kurtosis extends ArithmeticOperation<Double> {

    public Kurtosis(List<Double> list) {
        super(list);
    }

    @Override
    public Double calculate() {
        StandardDeviation objStd = new StandardDeviation();

        double standardDeviation, sumFourthPower;
        int n;

        n = this.getList().size();
        standardDeviation = objStd.calculate(list);
        sumFourthPower = Tools.sumOfPowers(this.getList(), 4);
        kurtosis = sumFourthPower / ((n - 1) * Math.pow(standardDeviation, 4));

        return kurtosis;
    }
}