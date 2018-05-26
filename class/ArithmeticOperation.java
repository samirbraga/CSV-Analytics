import java.util.List;

public abstract class ArithmeticOperation<T> {
    
    private List<T> list;
    private List<T> optList;


    public ArithmeticOperation(List<T> list) {
        this.list = list;
    }

    public ArithmeticOperation(List<T> list, List<T> optList) {
        this.list = list;
        this.optList = optList;
    }

    public List<T> getList() {
        return this.list;
    }

    public List<T> getOptList() {
        return this.optList;
    }

    public abstract T calculate();
}