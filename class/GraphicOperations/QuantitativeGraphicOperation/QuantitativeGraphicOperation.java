import java.util.List;
import java.util.Map;
//Essa classe deve importar a classe GraphicOperations.
public abstract class QuantitativeGraphicOperation extends GraphicOperation{

	public QuantitativeGraphicOperation(String[] labels, List<Map> data){
		super(labels,data);
	}

	public abstract Object calculate();
}
