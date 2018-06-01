package br.com.csvanalytics.controller;

import br.com.csvanalytics.model.Session;

import java.util.Map;

public class CSVController {
	static Map<String, Double> AverageCalculate(String token) {
	    Map selectedSession = Session.getSession(token);

    }
}
