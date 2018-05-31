import java.util.List;

/**
 * Auxiliary class
 */
public abstract class SumOfPowers {

    /**
     * Calculates the sum of powers of a list
     * @param list List x1,...,xn with n values
     * @param power The desired power
     * @return The sum of all (xi - mean)^power of a list
     */
    public static double calculate(List<Double> list, int power) {
        double listMean = Mean.calculate(list);
        double sumOfPowers = 0;

        for (double value : list) {
            sumOfPowers += Math.pow(value - listMean, power);
        }

        return sumOfPowers;
    }
}
