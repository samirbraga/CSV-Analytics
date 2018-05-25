import java.util.List;

public abstract class ArithmeticOperation {
    private List<Double> list;
    private List<Double> optList;


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

    public abstract double calculate();
}