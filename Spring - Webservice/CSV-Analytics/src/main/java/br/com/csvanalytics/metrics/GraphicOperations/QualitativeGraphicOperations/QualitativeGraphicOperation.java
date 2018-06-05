package br.com.csvanalytics.metrics.GraphicOperations.QualitativeGraphicOperations;

import java.util.List;
import java.util.Map;

import br.com.csvanalytics.metrics.GraphicOperations.GraphicOperation;

public abstract class QualitativeGraphicOperation extends GraphicOperation {

	public QualitativeGraphicOperation(String[] labels, List<Map> data) {
		super(labels, data);
	}

	public abstract Object calculate();
}