import java.util.List;

/**
 * Represents operations that returns double values.
 */
public abstract class ArithmeticOperation {
    
    private List<Double> list;
    private List<Double> optList;    // for multivariate metrics


    public ArithmeticOperation(List<Double> list) {
        this.list = list;
    }

    public ArithmeticOperation(List<Double> list, List<Double> optList) {
        this.list = list;
        this.optList = optList;
    }

    public List<Double> getList() {
        return this.list;
    }

    public List<Double> getOptList() {
        return this.optList;
    }

    /**
     * Commom method for all classes, calculates the specific metric.
     */
    public abstract double calculate();
}