package br.com.csvanalytics.metrics.GraphicOperations;

import java.util.List;
import java.util.Map;

public abstract class QualitativeGraphicOperation extends GraphicOperation {

	public QualitativeGraphicOperation(String[] labels, List<Map> data) {
		super(labels, data);
	}

	public abstract Object calculate();
}