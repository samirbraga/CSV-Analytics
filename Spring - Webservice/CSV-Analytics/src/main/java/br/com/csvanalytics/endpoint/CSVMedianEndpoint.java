package br.com.csvanalytics.endpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/calc")
public class CSVMedianEndpoint {
    @RequestMapping(method = RequestMethod.GET, path = "/median")
    public Map<String, Double> getFullDataFromColumns() {
        Map<String, Double> myMap = new HashMap<String, Double>();
        myMap.put("notas", 9.0);
        return myMap;
    }
}
