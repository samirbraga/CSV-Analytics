package br.com.csvanalytics.endpoint;

import java.util.Map;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.csvanalytics.controller.CSVController;
import br.com.csvanalytics.model.Session;

@RestController
@RequestMapping("api/calc")
public class CSVQualitativeFrequencyTableEndpoint {
    @RequestMapping(method = RequestMethod.GET, path = "/qualitative-frequency-table")
    public Map<String, List> queryMethod(@RequestParam String token) {
        if (Session.checkExistence(token)) {
            Map<String, List> frequencyTable = CSVController.qualitativeFrequencyTableCalculate(token);
            return frequencyTable;
        }

        return null;
    }
}
