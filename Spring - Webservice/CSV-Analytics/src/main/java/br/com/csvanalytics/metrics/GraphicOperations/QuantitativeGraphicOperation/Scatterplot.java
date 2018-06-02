package br.com.csvanalytics.metrics.GraphicOperations.QuantitativeGraphicOperation;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Scatterplot extends QuantitativeGraphicOperation {

    public Scatterplot(String[] labels, List<Map> data){
		super(labels, data);
    }
    
    @Override
    public Object calculate() {
        String[] header = (String[]) this.getLabels();
        List<Map> csv = this.getData();

        List<Map> scatterplot = new ArrayList<Map>();

        for (Map line : csv) {
            Map<String, Double> newLine = new HashMap<String, Double>();

            for (String column : header) {
                newLine.put(column, (Double) line.get(column));
            }

            scatterplot.add(newLine);
        }

        return scatterplot;
    }
}