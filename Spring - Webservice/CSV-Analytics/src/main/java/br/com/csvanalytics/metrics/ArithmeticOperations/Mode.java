package br.com.csvanalytics.metrics.ArithmeticOperations;

import java.util.*;

public class Mode<T> {
    List<T> list;
    
    public Mode(List<T> list) {
        this.list = list;
    }
    
    public List<T> getList() {
        return this.list;
    }
    
    public List<String> calculate() {  
        int frequency = 0, value, maxFrequency;

        List<String> modesArray = new ArrayList<String>();
        List<Integer> frequencyArray = new ArrayList<Integer>();
        
        for (T i : this.getList()) {
            for (T j : this.getList()) {
                if (i.equals(j)) {
                    frequency++;
                }
            }

            frequencyArray.add(frequency);
            frequency = 0;
        }
        
        maxFrequency = Collections.max(frequencyArray, null);
        
        while (frequencyArray.contains(maxFrequency)) {
            value = frequencyArray.indexOf(maxFrequency);
            frequencyArray.add(value,0);
            frequencyArray.remove(value + 1);
            
            if (!modesArray.contains(this.getList().get(value).toString())) {
                modesArray.add((this.getList().get(value)).toString());
            }
        }
        
        return modesArray;
    }
}
