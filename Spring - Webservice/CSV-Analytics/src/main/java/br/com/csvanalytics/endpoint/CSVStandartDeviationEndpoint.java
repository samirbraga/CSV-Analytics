package br.com.csvanalytics.endpoint;

import br.com.csvanalytics.model.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/calc")
public class CSVStandartDeviationEndpoint {
    @RequestMapping(method = RequestMethod.GET, path = "/standart-deviation")
    public Map<String, Double> queryMethod(@RequestParam String hash) {
        System.out.println("id=" + hash);
        if (Session.checkExistence(hash)){
            Map<String, Double> myMap = new HashMap<String, Double>();
            myMap.put(hash, 9.0);
            return myMap;
        }

        return null;
    }
}
