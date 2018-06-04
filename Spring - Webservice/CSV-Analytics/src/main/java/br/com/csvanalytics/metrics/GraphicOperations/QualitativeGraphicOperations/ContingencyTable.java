package br.com.csvanalytics.metrics.GraphicOperations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContingencyTable extends QualitativeGraphicOperation {
	String selectedLabel = null;

	public ContingencyTable(String[] labels, List<Map> data, String selectedLabel) {
		super(labels, data);
		this.selectedLabel = selectedLabel;
	}
	
	@Override
	public Object calculate() {		
		String selected = this.selectedLabel;
		int selectedIndex = Arrays.asList(this.labels).indexOf(selected);
		List<String> selectedColumn = distinct(this.data, selected);

		Map distincts = new HashMap<String, List>();
		for (String title : this.labels) {
			if (!title.equals(selected)) {
				distincts.put(title, distinct(this.data, title));
			}
		}

		Map contingency = new HashMap<String, Map>();

		for (String title : this.labels) {
			if (!title.equals(selected)) {
				String key = title;
				List<String> values = (ArrayList<String>) distincts.get(title);

				Map distinct = new HashMap<String, Map>();
				for (String value : values) {
					Map<String, Integer> counting = new HashMap<String, Integer>();
					for (String cell : selectedColumn) {
						counting.put(cell, 0);
					}
					distinct.put(value, counting);
				}

				contingency.put(key, distinct);
			}
		}

		for (int i = 0; i < this.labels.length; i++) {
			String title = this.labels[i];

			if (!title.equals(selected)) {
				for (int j = 0; j < this.data.size(); j++) {
					Map row = (Map)this.data.get(j);
					String value = (String)row.get(title);
					String selectedValue = (String)row.get(selected);

					Map currentCounting = (Map) ((Map) contingency.get(title)).get(value);
					Integer currentValue = (Integer) currentCounting.get(selectedValue);
					currentCounting.put(selectedValue, currentValue + 1);
				}
			}
		}

		return contingency;
	}

	List<String> distinct(List<Map> records, String label) {
		List<String> selectedColumn = new ArrayList<String>();
		for (Map row : records) {
			String cell = (String)row.get(label);
			if (!selectedColumn.contains(cell)) {
				selectedColumn.add(cell);
			}
		}

		return selectedColumn;
	}
}