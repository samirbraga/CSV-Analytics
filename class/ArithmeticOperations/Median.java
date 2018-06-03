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

    private void sortList(List<Double> list) {

        boolean flag;

        for(int i = 0; i < list.size(); i++) {

            flag = false;

            for(int j = i +1; j<list.size() && (flag == false);j++) {

                if(list.get(i) > list.get(j)) {
                    Collections.swap(list,i,j);
                    flag = true;
                }
            }
        }
    }

    @Override
    public double calculate() {

        double median;
        sortList(this.getList());
        int n = getList().size();

        if(n%2 == 0)
          median = ((this.getList()).get(n/2) + (this.getList()).get(n/2 - 1))/2;
        else
          median = (this.getList()).get((Integer) n/2);

        return median;
    }
}
