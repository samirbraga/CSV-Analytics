import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

public class ContingencyTable extends QualitativeGraphicOperation{


	public ContingencyTable(List<String> labels, List<Map> data){
		super(labels,data);
	}
	//Esse método pegará os dados contidos no arquivo csv junto com as labels dos campos qualitativos e construirá a tabela de contigência.
	@Override
	public List<Map> calculate(){
		List<Map> contigencyTable = new ArrayList<Map> (); //Tabela de contingência que será retornada
		List<String> header = this.getLabels();//Cabeçalho
		List<Map> data = this.getData();//Matriz de dados do csv
		Map<String,Set> valuesList = new HashMap<String,Set>(); //Um dicionário onde cada chave é um elemento do cabeçalho e o seu valor é um set dos seus posíveis valores

		for(Iterator labels = header.listIterator(); labels.hasNext();){
			String key = (String) labels.next();
			Set<String> set = new HashSet<String>();
			valuesList.put(key,set);
		}

		for(Iterator types = data.listIterator(); types.hasNext();){
			Map row = (Map) matriz.next();
			for(Iterator key = header.listIterator();key.hasNext();){
				String str = (String) key.next();
				Set<String> set = new HashSet<String>();
				set.add( row.get( str ) );
				set.add( valuesList.get( str ) );
				valuesList.put(str,set);
			}
		}

		return contigencyTable;
	}

}