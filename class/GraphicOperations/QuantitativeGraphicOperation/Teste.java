import java.util.*;
class Teste{
	public static void main(String[] args) {
		String[] header = {"nome"};
		List<Map> csv = new ArrayList<Map>();
		Map<String,String> row = new HashMap<String,String>();
		row.put("nome","Matheus");
		row.put("nacionalidade","2");
		row.put("sexo","M");
		csv.add(row);
		row = new HashMap<String,String>();
		row.put("nome","Natan");
		row.put("nacionalidade","1");
		row.put("sexo","M");
		csv.add(row);
		row = new HashMap<String,String>();
		row.put("nome","Ana");
		row.put("nacionalidade","2");
		row.put("sexo","F");
		csv.add(row);
		row = new HashMap<String,String>();
		row.put("nome","Joyce");
		row.put("nacionalidade","5");
		row.put("sexo","F");
		csv.add(row);
		row = new HashMap<String,String>();
		row.put("nome","Mirna");
		row.put("nacionalidade","0");
		row.put("sexo","F");
		csv.add(row);

		FrequencyTableQuantitative e = new FrequencyTableQuantitative(header,csv);
		List<String[]> tabela = (List<String[]>) e. calculate();
		for(String[] vetor : tabela){
			for(String str : vetor){
				System.out.print(str+" ");
			}
			System.out.println("");
		}

	}
}
