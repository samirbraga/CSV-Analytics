import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Math;
import java.text.DecimalFormat;

class FrequencyTable extends QuantitativeGraphicOperation{


    public FrequencyTable(String[] labels, List<Map> data){
        super(labels,data);
    }

    @Override
    public Object calculate(){
        String[] header = (String[]) this.getLabels(); //Cabeçalho do csv
        List<Map> csv = this.getData(); //Dados do csv

        Map<String,Map> frequencyTable = new HashMap<String,Map>();
        double totalF = 0;
        double totalf = 0;

        //Nesse for se percorre linha por linha da matriz e coloca em frequencyTable a classe e a frequência absoluta dela.
        for(Iterator matriz = csv.listIterator(); matriz.hasNext();){
            Map linha = (Map) matriz.next(); 
            for(String label: header){
                String key = (String) linha.get(label);
                if(!frequencyTable.containsKey( label )){
                    Map<String,Integer> valores = new HashMap<String,Integer>();
                    valores.put(key,1);
                    frequencyTable.put(label,valores);
                    totalF += 1;
                }
                else{
                    if(!((Map)frequencyTable.get(label)).containsKey(key)){
                        Map<String,Integer> aux = (Map<String,Integer>) frequencyTable.get(label);
                        aux.put(key,1);
                        frequencyTable.put(label,aux);
                        totalF += 1;
                    }
                    else{
                        Map<String,Integer> aux = (Map<String,Integer>) frequencyTable.get(label);
                        aux.put(key, (Integer) aux.get(key)+1 );
                        frequencyTable.put(label, aux );
                        totalF += 1;
                    }
                }
            }

        }
        
        DecimalFormat df = new DecimalFormat("0.0000"); //Essa classe é usada para se delimitar o número de casas decimais 
        int acumulador = 0;//Serve para contar a frequência absoluta acumulada
       
        /*A frequencyTableFinal é um ArrayList onde cada elemento é um vetor de Strings onde o 
        primeiro elemento é a classe, o segundo a frequência absoluta, 
        o terceiro a frequência relativa, o quarto a 
        frequência absoluta acumulada e o quinto a frequência relativa acumulada.*/

        List<String[]> frequencyTableFinal = new ArrayList<String[]>();
        Iterator keys = frequencyTable.keySet().iterator();
        int i = 0;
        while (keys.hasNext()) {
            String quantifier = (String) keys.next();
            Iterator it = frequencyTable.get(quantifier).entrySet().iterator();
            while(it.hasNext()){
                Map.Entry pair = (Map.Entry)it.next();
                double registerCalc = (Integer)pair.getValue()/totalF;
                totalf += registerCalc;
                acumulador += (Integer) pair.getValue();
                String[] str = {String.valueOf(pair.getKey()),String.valueOf(df.format(pair.getValue())),String.valueOf(df.format(registerCalc)),String.valueOf(acumulador),String.valueOf(df.format(totalf))};
                frequencyTableFinal.add(str);
                it.remove();
            }
            keys.remove();
        }
        String[] str = {"Total",String.valueOf(totalF),String.valueOf(Math.round(totalf)),"",""};
        frequencyTableFinal.add(str);
        return frequencyTableFinal;
    }
}