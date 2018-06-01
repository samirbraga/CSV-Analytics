import java.util.List;
import java.util.Map;

public abstract class QualitativeGraphicOperation extends GraphicOperation{

	public QualitativeGraphicOperation(String[] labels, List<Map> data){
		super(labels,data);
	}

	public abstract List<String[]> calculate();
}