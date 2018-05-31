import java.util.List;

/**
 * The largest value in a sample data set
 */
public class Max extends ArithmeticOperation {

    public Max(List<Double> list) {
        super(list);
    }

    @Override
    public double calculate() {

        double num = (this.getList()).get(0);

        for(double i: this.getList()) {

            if(i>num)
              num = i;
        }

        return num;
    }
}
