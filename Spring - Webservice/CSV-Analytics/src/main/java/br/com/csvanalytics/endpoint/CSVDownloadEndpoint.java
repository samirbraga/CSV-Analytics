package br.com.csvanalytics.endpoint;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.csvanalytics.model.Session;

@RestController
@RequestMapping("api/calc")
public class CSVDownloadEndpoint {
    @RequestMapping(method = RequestMethod.GET, path = "/download")
    public Map<String, Map> queryMethod(@RequestParam String token) {
        if (Session.checkExistence(token)) {
            Map session = (Map)Session.getSession(token);
            Map<String, Map> json = new HashMap<String, Map>();

            Map<String, Object[]> data = new HashMap<String, Object[]>();
            if (session.get("header") != null) {
                data.put("header", (String[]) session.get("header"));
            }
            if (session.get("records") != null) {
                data.put("records", (Map[]) session.get("records"));
            }
            if (session.get("quantitatives") != null) {
                data.put("quantitatives", (String[])session.get("quantitatives"));
            }
            if (session.get("qualitatives") != null) {
                data.put("qualitatives", (String[])session.get("qualitatives"));
            }
            
            Map<String, Integer> stats = new HashMap<String, Integer>();
            if (session.get("header") != null) {
                stats.put("num_of_columns", ((String[])session.get("header")).length);
            }
            if (session.get("records") != null) {
                stats.put("num_of_rows", ((Map[])session.get("records")).length);
            }

            Map<String, Map> metrics = new HashMap<String, Map>();
            if (session.get("skewness") != null) {
                metrics.put("skewness", (Map) session.get("skewness"));
            }
            if (session.get("boxplot") != null) {
                metrics.put("boxplot", (Map) session.get("boxplot"));
            }
            if (session.get("contingencyTable") != null) {
                metrics.put("contingencyTable", (Map) session.get("contingencyTable"));
            }
            if (session.get("kurtosis") != null) {
                metrics.put("kurtosis", (Map) session.get("kurtosis"));
            }
            if (session.get("graphicBar") != null) {
                metrics.put("graphicBar", (Map) session.get("graphicBar"));
            }
            if (session.get("histogram") != null) {
                metrics.put("histogram", (Map) session.get("histogram"));
            }
            if (session.get("max") != null) {
                metrics.put("max", (Map) session.get("max"));
            }
            if (session.get("min") != null) {
                metrics.put("min", (Map) session.get("min"));
            }
            if (session.get("median") != null) {
                metrics.put("median", (Map) session.get("median"));
            }
            if (session.get("average") != null) {
                metrics.put("average", (Map) session.get("average"));
            }
            if (session.get("mode") != null) {
                metrics.put("mode", (Map) session.get("mode"));
            }
            if (session.get("scatterPlot") != null) {
                metrics.put("scatterPlot", (Map) session.get("scatterPlot"));
            }
            if (session.get("variance") != null) {
                metrics.put("variance", (Map) session.get("variance"));
            }
            if (session.get("covariance") != null) {
                metrics.put("covariance", (Map) session.get("covariance"));
            }
            if (session.get("quantitativeFrequencyTable") != null) {
                metrics.put("quantitativeFrequencyTable", (Map) session.get("quantitativeFrequencyTable"));
            }
            if (session.get("qualitativeFrequencyTable") != null) {
                metrics.put("qualitativeFrequencyTable", (Map) session.get("qualitativeFrequencyTable"));
            }
            if (session.get("standartDeviation") != null) {
                metrics.put("standartDeviation", (Map) session.get("standartDeviation"));
            }
            if (session.get("correlationCoefficient") != null) {
                metrics.put("correlationCoefficient", (Map) session.get("correlationCoefficient"));
            }

            json.put("data", data);
            json.put("metrics", metrics);
            json.put("stats", stats);

            return json;
        }

        return null;
    }
}   