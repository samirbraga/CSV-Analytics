package br.com.csvanalytics.metrics.GraphicOperations.QuantitativeGraphicOperation;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Scatterplot extends QuantitativeGraphicOperation {

    public Scatterplot(String[] labels, List<Map> data) {
		super(labels, data);
    }
    
    @Override
    public Object calculate() {
        String[] header = (String[]) this.getLabels();
        List<Map> csv = this.getData();

        List<String[]> scatterplot = new ArrayList<String[]>();

        for (Map line : csv) {
            if (!header[0].equals(header[1])) {
                String[] newLine = new String[2];
    
                newLine[0] = (String)line.get(header[0]);
                newLine[1] = (String)line.get(header[1]);
    
                scatterplot.add(newLine);
            }
        }

        return scatterplot;
    }
}