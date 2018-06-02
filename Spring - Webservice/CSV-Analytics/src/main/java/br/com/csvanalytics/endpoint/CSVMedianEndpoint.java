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
public class CSVMedianEndpoint {
    @RequestMapping(method = RequestMethod.GET, path = "/median")
    public Map<String, Double> queryMethod(@RequestParam String token) {
        System.out.println("token=" + token);
        if (Session.checkExistence(token)) {
            Map<String, Double> median = CSVController.medianCalculate(token);
            return median;
        }

        return null;
    }
}