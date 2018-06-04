package br.com.csvanalytics.endpoint;

import br.com.csvanalytics.model.CSVReader;
import br.com.csvanalytics.model.RandomString;
import br.com.csvanalytics.model.Session;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class CSVReaderEndpoint {
    @RequestMapping(method = RequestMethod.POST, path = "/upload-csv", consumes = {"multipart/form-data"})
    @ResponseBody
    public Map<String, Object[]> receiveFile(@Valid @RequestParam("file") MultipartFile multipart) throws IOException {

        File convFile = new File(multipart.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipart.getBytes());
        fos.close();

        CSVReader CSV = new CSVReader(convFile);
        Map data = new HashMap<String, Object[]>();

        CSV.parse();

        String[] header = CSV.getHeader();
        data.put("header", header);

        String[][] records = CSV.getRecords();
        int recordsLen = records.length;
        List<Map> docs = new ArrayList<Map>();
        for (int i = 0; i < recordsLen; i++) {
            Map row = new HashMap<String, String>();
            for (int j = 0; j < header.length; j++) {
                row.put(header[j], records[i][j]);
            }
            docs.add(row);
        }

        String[] tokens = {""};
        tokens[0] = new RandomString().nextString();
        data.put("token", tokens);

        List<String> qualitatives = new ArrayList<String>();
        List<String> quantitatives = new ArrayList<String>();

        for (int i = 0; i < header.length; i++) {
            try {
                Double aux = Double.parseDouble(records[0][i]);
                quantitatives.add(header[i]);
            } catch (Exception e) {
                qualitatives.add(header[i]);
            }

            // if (quantitatives.contains(header[i])) {
            //     int n = 0;
            //     for (int j = 0; j < records.length; j++) {
            //         try {
            //             Double aux = Double.parseDouble(records[j][i]);
            //             docs.remove(i - n);
            //             n++;
            //         } catch (Exception e) {
            //             // 
            //         }
            //     }
            // }
        }

        Map[] docsArr = new Map[docs.size()];
        docsArr = docs.toArray(docsArr);

        data.put("records", docsArr);

        String[] qualitArr = new String[qualitatives.size()];
        qualitArr = qualitatives.toArray(qualitArr);
        data.put("qualitatives", qualitArr);

        String[] quantitArr = new String[quantitatives.size()];
        quantitArr = quantitatives.toArray(quantitArr);
        data.put("quantitatives", quantitArr);

        Session.putSession(tokens[0], data);

        return data;
    }
}

        