import java.util.List;

/**
 * The smallest value in a sample data set
 */
public class Min extends ArithmeticOperation {

    public Min(List<Double> list) {
        super(list);
    }

    @Override
    public double calculate() {

      double num = (this.getList()).get(0);

      for(double i: this.getList()) {
        
          if(i<num)
            num = i;
      }

      return num;
    }
}