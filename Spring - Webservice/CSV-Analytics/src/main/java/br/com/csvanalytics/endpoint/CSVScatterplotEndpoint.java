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
public class CSVScatterplotEndpoint {
    @RequestMapping(method = RequestMethod.GET, path = "/scatterplot")
    public Map<String, Object> queryMethod(@RequestParam String token) {
        System.out.println("token=" + token);
        if (Session.checkExistence(token)){
            Map<String, Double> myMap = new HashMap<String, Double>();
            myMap.put(token, 9.0);
            return myMap;
        }

        Map fallback = new HashMap<String, String>();
        fallback.put("status", "error");
        fallback.put("message", "Token not found.");
        return fallback;
    }
}
