package br.com.csvanalytics.endpoint;

import br.com.csvanalytics.model.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.csvanalytics.controller.CSVController;

import java.util.Map;

@RestController
@RequestMapping("api/calc")
public class CSVCorrelationCoefficientEndpoint {
    @RequestMapping(method = RequestMethod.GET, path = "/correlation-coefficient")
    public Map queryMethod(@RequestParam String token) {
        if (Session.checkExistence(token)) {
            Map pearsonCoefficient = CSVController.pearsonCoefficientCalculate(token);
            return pearsonCoefficient;
        }

        return null;
    }
}
