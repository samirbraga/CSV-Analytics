package br.com.csvanalytics.model;

import java.util.*;
import java.io.*;

public class CSVReader {
    BufferedReader mainFile = null;
    List<String> header = new ArrayList<String>();
    List<String[]> records = new ArrayList<String[]>();


    public CSVReader(BufferedReader file){
        mainFile = file;
    }

    public CSVReader(File file) throws IOException {
        file.createNewFile();
        FileReader readFile = new FileReader(file);
        BufferedReader bufFile = new BufferedReader(readFile);
        mainFile = bufFile;
    }

    public void parse() {
        try {
            String[] headings = mainFile.readLine().split(",");
            for (String heading : headings) {
                header.add(heading);
            }
            String line = null;
            while ((line = mainFile.readLine()) != null) {
                records.add(line.split(","));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getHeader() {
        String[] headerArr = new String[header.size()];
        headerArr = header.toArray(headerArr);
        return headerArr;
    }

    public String[][] getRecords() {
        String[][] recordsArr = new String[records.size()][header.size()];
        recordsArr = records.toArray(recordsArr);
        return recordsArr;
    }
}