import java.util.Arrays;
import java.util.List;

public class Test {

    public static List<Double> odd = Arrays.asList(-1.0, -1.0, 0.0, 1.0, 1.0);
    public static List<Double> even = Arrays.asList(-1.0, -1.0, 1.0, 1.0);

    public static void testCovariance() {
        System.out.println("Covariance");
        Covariance o = new Covariance(odd, odd);
        System.out.printf("%.2f == 0.00%n", o.calculate());
        Covariance e = new Covariance(even, even);
        System.out.printf("%.2f == 0.00%n", e.calculate());
    }

    public static void testKurtosis() {
        System.out.println("Kurtosis");
        Kurtosis o = new Kurtosis(odd);
        System.out.printf("%.2f == 1.00%n", o.calculate());
        Kurtosis e = new Kurtosis(even);
        System.out.printf("%.2f == 0.75%n", e.calculate());
    }

    public static void testMax() {
        System.out.println("Max");
        Max o = new Max(odd);
        System.out.printf("%.2f == 1.00%n", o.calculate());
        Max e = new Max(even);
        System.out.printf("%.2f == 1.00%n", e.calculate());
    }

    public static void testMean() {
        System.out.println("Mean");
        Mean o = new Mean(odd);
        System.out.printf("%.2f == 0.00%n", o.calculate());
        Mean e = new Mean(even);
        System.out.printf("%.2f == 0.00%n", e.calculate());
    }

    public static void testMedian() {
        System.out.println("Median");
        Median o = new Median(odd);
        System.out.printf("%.2f == 0.00%n", o.calculate());
        Median e = new Median(even);
        System.out.printf("%.2f == 0.00%n", e.calculate());
    }

    public static void testMin() {
        System.out.println("Min");
        Min o = new Min(odd);
        System.out.printf("%.2f == -1.00%n", o.calculate());
        Min e = new Min(even);
        System.out.printf("%.2f == -1.00%n", e.calculate());
    }

    public static void testMode() {
        System.out.println("Mode");
        Mode o = new Mode(odd);
        System.out.printf("%s == -1.00, 1.0%n", o.calculate());
        Mode e = new Mode(even);
        System.out.printf("%s == -1.00, 1.0%n", e.calculate());
    }

    public static void testPearsonCoefficient() {
        System.out.println("Pearson");
        PearsonCoefficient o = new PearsonCoefficient(odd, odd);
        System.out.printf("%.2f == 1.00%n", o.calculate());
        PearsonCoefficient e = new PearsonCoefficient(even, even);
        System.out.printf("%.2f == 1.00%n", e.calculate());
    }

    public static void testSkewness() {
        System.out.println("Skewness");
        Skewness o = new Skewness(odd);
        System.out.printf("%.2f == 0.00%n", o.calculate());
        Skewness e = new Skewness(even);
        System.out.printf("%.2f == 0.00%n", e.calculate());
    }

    public static void testStandardDeviation() {
        System.out.println("StdDeviation");
        StandardDeviation o = new StandardDeviation(odd);
        System.out.printf("%.2f == 1.00%n", o.calculate());
        StandardDeviation e = new StandardDeviation(even);
        System.out.printf("%.2f == 1.15%n", e.calculate());
    }

    public static void testVariance() {
        System.out.println("Variance");
        Variance o = new Variance(odd);
        System.out.printf("%.2f == 1.00%n", o.calculate());
        Variance e = new Variance(even);
        System.out.printf("%.2f == 1.33%n", e.calculate());
    }

    public static void main(String[] args) {
        System.out.printf("Univariate:%n%n");
        testKurtosis();
        testMax();
        testMean();
        testMedian();
        testMin();
        testMode();
        testSkewness();
        testStandardDeviation();
        testVariance();
        System.out.printf("%nMultivariate:%n%n");
        testCovariance();
        testPearsonCoefficient();
    }
}