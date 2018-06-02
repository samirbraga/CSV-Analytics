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
public class CSVMaxEndpoint {
    @RequestMapping(method = RequestMethod.GET, path = "/max")
    public Map<String, Double> queryMethod(@RequestParam String token) {
        if (Session.checkExistence(token)) {
            Map<String, Double> max = CSVController.maxCalculate(token);
            return max;
        }

        return null;
    }
}
