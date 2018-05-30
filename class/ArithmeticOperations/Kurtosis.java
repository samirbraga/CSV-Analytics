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
        // IMPLEMENTAR
        return -200.0;
}