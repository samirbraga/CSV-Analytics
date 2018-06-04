package br.com.csvanalytics.model;

import java.util.*;
import java.io.*;

public class CSVReader {
    BufferedReader mainFile = null;
    List<String> header = new ArrayList<String>();
    List<String[]> records = new ArrayList<String[]>();

    public CSVReader(BufferedReader file) {
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
                String[] cells = line.split(",");

                boolean hasNull = false;
                for (int i = 0; i < cells.length; i++) {
                    cells[i] = trim(cells[i], '"');
                    cells[i] = trim(cells[i], ' ');

                    if (cells[i] == "") {
                        hasNull = false;
                    }
                }

                if (cells.length == headings.length && !hasNull) {
                    records.add(cells);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String trim(String str, char a) {
        String[] chars = str.split("");
        List<String> charsList = new ArrayList<String>();

        for (String c : chars) {
            charsList.add(c);
        }

        for (int i = 0; isSame(charsList.get(i), a); i++) {
            charsList.remove(i);
            i--;
        }

        for (int i = charsList.size() - 1; isSame(charsList.get(i), a); i--) {
            charsList.remove(i);
        }

        chars = new String[charsList.size()];
        chars = charsList.toArray(chars);

        return String.join("", chars);
    }
 
    public boolean isSame(String s, char c) {
        if (s != null && s.length() == 1) { 
            return s.charAt(0) == c;
        }
        return false;
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