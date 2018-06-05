import java.util.List;
import java.util.Collections;

/**
 * The numeric value separating the higher half of the ordered sample data from the lower half;
 * if n is odd the median is the center value.  If n is even the median is the average of the 2 center values
 */
public class Median extends ArithmeticOperation {

    public Median(List<Double> list) {
        super(list);
    }

    @Override
    public double calculate() {

        double median;
        Collections.sort(this.getList(),null);
        int n = getList().size();

        if(n%2 == 0)
          median = ((this.getList()).get(n/2) + (this.getList()).get(n/2 - 1))/2;
        else
          median = (this.getList()).get((Integer) n/2);

        return median;
    }
}
