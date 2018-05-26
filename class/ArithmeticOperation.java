import java.util.List;

public abstract class ArithmeticOperation<T> {
    
    private List<?> list;
    private List<?> optList;    // for multivariate metrics


    public ArithmeticOperation(List<?> list) {
        this.list = list;
    }

    public ArithmeticOperation(List<?> list, List<?> optList) {
        this.list = list;
        this.optList = optList;
    }

    public List<?> getList() {
        return this.list;
    }

    public List<?> getOptList() {
        return this.optList;
    }

    public abstract T calculate();
}