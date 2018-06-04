package br.com.csvanalytics.metrics.GraphicOperations;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class FrequencyTable extends QualitativeGraphicOperation {

    public FrequencyTable(String[] labels, List<Map> data){
        super(labels, data);
    }

    @Override
    public List<String[]> calculate() {
        //A tabela de frequência de dados qualitativos é composta por 3 colunas: coluna das categorias ou classes – onde se indicam as categorias observadas para a variável em estudo; coluna das frequências absolutas – onde se regista o total de elementos da amostra que pertencem a cada categoria e coluna das frequências relativas – onde se coloca, para cada categoria, a sua frequência relativa.
        Map<String,Map> frequencyTable = new HashMap<String,Map>();
        double totalF = 0; //Somatória das frequências absolutas
        double totalf = 0; //Somatória das frequências relativas
        String[] header = (String[]) this.getLabels(); //Cabeçalho do csv
        List<Map> csv = this.getData(); //Dados do csv

        //Nesse laço eu percorro linha por linha de csv.
        for (Iterator matriz = csv.listIterator(); matriz.hasNext();) {
            Map linha = (Map) matriz.next(); //Pego uma linha
            for (String label: header) { //Faço uma verificação com todos os cabeçalhos
                String key = (String)linha.get(label);
                if (!frequencyTable.containsKey( label )) {
                    Map<String,Integer> valores = new HashMap<String,Integer>();
                    valores.put(key,1);
                    frequencyTable.put(label,valores);
                    totalF += 1;
                } else {
                    if (!((Map)frequencyTable.get(label)).containsKey(key)) {
                        Map<String,Integer> aux = (Map<String,Integer>) frequencyTable.get(label);
                        aux.put(key,1);
                        frequencyTable.put(label,aux);
                        totalF += 1;
                    } else {
                        Map<String,Integer> aux = (Map<String,Integer>) frequencyTable.get(label);
                        aux.put(key, (Integer) aux.get(key)+1 );
                        frequencyTable.put(label, aux );
                        totalF += 1;
                    }
                }
            }

        }

        List<String[]> frequencyTableFinal = new ArrayList<String[]>();
        Iterator keys = frequencyTable.keySet().iterator();
        int i = 0;

        while (keys.hasNext()) {
            String quantifier = (String) keys.next();
            Iterator it = frequencyTable.get(quantifier).entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                double registerCalc = (Integer)pair.getValue()/totalF;
                totalf += registerCalc;
                String[] str = {String.valueOf(pair.getKey()),String.valueOf(pair.getValue()),String.valueOf(registerCalc)};
                frequencyTableFinal.add(str);
                it.remove();
            }

            keys.remove();
        }

        String[] str = { "Total", String.valueOf(totalF), String.valueOf(totalf) };
        frequencyTableFinal.add(str);

        return frequencyTableFinal;
    }
}
