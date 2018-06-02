import java.util.*;
class Teste{
	public static void main(String[] args) {
		String[] header = {"salario"};
		List<Map> csv = new ArrayList<Map>();
		Map<String,String> row = new HashMap<String,String>();
		row.put("salario","2001");
		row.put("n_filhos","2");
		row.put("gastos","1500");
		csv.add(row);
		row = new HashMap<String,String>();
		row.put("salario","200");
		row.put("n_filhos","1");
		row.put("gastos","500");
		csv.add(row);
		row = new HashMap<String,String>();
		row.put("salario","2002");
		row.put("n_filhos","2");
		row.put("gastos","500");
		csv.add(row);
		row = new HashMap<String,String>();
		row.put("salario","5001");
		row.put("n_filhos","5");
		row.put("gastos","4500");
		csv.add(row);
		row = new HashMap<String,String>();
		row.put("salario","6000");
		row.put("n_filhos","0");
		row.put("gastos","90");
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
