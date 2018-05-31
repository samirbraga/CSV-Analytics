import java.util.List;

/**
 * The sum of the cubed differences between data values and the mean, divided by the count minus 1 times the cubed standard deviation
 */
public class Skewness extends ArithmeticOperation {

    public Skewness(List<Double> list) {
        super(list);
    }

    public double calculate() {
        List<Double> list = this.getList();

        double standardDeviation = new StandardDeviation(list).calculate();
        double sum = SumOfPowers.calculate(list, 3);
        int n = list.size(); 

        return sum / ((n - 1) * Math.pow(standardDeviation, 3));
    }
}