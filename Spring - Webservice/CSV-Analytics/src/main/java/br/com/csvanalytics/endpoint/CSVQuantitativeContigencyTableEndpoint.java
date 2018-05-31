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
public class CSVQuantitativeContigencyTableEndpoint {
    @RequestMapping(method = RequestMethod.GET, path = "/contigency-table-2")
    public Map<String, Double> queryMethod(@RequestParam String token) {
        System.out.println("token=" + token);
        if (Session.checkExistence(token)){
            Map<String, Double> myMap = new HashMap<String, Double>();
            myMap.put(token, 9.0);
            return myMap;
        }

        return null;
    }
}
