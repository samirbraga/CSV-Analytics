package br.com.csvanalytics.metrics.GraphicOperations.QuantitativeGraphicOperation;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Histogram extends QuantitativeGraphicOperation {

    public Histogram(String[] labels, List<Map> data) {
		super(labels, data);
    }
    
    @Override
    public Object calculate() {
        return new QuantitativeFrequencyTable(this.getLabels(), this.getData()).calculate();
    }
}