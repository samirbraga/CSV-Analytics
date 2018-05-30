/**
 * Auxiliary class
 */
public abstract final class Tools {

    /**
     * Calculates the sum of powers of a list
     * @param list List x1,...,xn with n values
     * @param power The desired power
     * @return The sum of all (xi - mean)^power of a list
     */
    public static double sumOfPowers(List<Double> list, int power) {
        Mean objMean = new Mean(list);

        double listMean = objMean.calculate(list);
        double sumOfPowers = 0;

        for (double value : list) {
            sum += Math.pow(value - listMean, power);
        }

        return sum;
    }
}