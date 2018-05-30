import java.util.List;
import java.util.Map;

public abstract class QualitativeGraphicOperation extends GraphicOperation{

	public QualitativeGraphicOperation(List<String> labels, List<Map> data){
		super(labels,data);
	}

	public abstract List<Map> calculate();
}