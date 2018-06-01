import java.util.List;
import java.util.Map;

public abstract class GraphicOperation<T>{
	private String[] labels; //Receberá uma lista contendo as labels dos elementos a serem calculados
	private List<Map> data; // Conterá a matriz com os dados a serem calculados, será uma matriz de hashMaps

	public GraphicOperation(String[] labels,List<Map> data){
		this.labels = labels;
		this.data = data;
	}

	public String[] getLabels() {
        return this.labels;
    }

    public List<Map> getData() {
        return this.data;
    }
	
    public abstract List<String[]> calculate();
}