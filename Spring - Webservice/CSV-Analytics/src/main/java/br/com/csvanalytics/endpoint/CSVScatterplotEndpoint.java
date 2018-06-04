package br.com.csvanalytics.endpoint;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.csvanalytics.controller.CSVController;
import br.com.csvanalytics.model.Session;

@RestController
@RequestMapping("api/calc")
public class CSVScatterplotEndpoint {
    @RequestMapping(method = RequestMethod.GET, path = "/scatterplot")
    public Map queryMethod(@RequestParam String token) {
        if (Session.checkExistence(token)) {
            Map ScatterPlot = CSVController.scatterPlotCalculate(token);
            return ScatterPlot;
        }

        return null;
    }
}
