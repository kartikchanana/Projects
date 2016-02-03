/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

/**
 *
 * @author Kartik
 */
public class Sort {
    
    public void Sort (int file_count, HashMap query, String result) 
    {
        HashMap<String, HashMap<Integer, Double>> query_score = query;
        File result_file = new File(result);
        int length = query_score.size();
        for (int i=0; i<length; i++)
        {
            String query_id="Q" + Integer.toString(i);
            HashMap<Integer, Double> qwe = query_score.get(query_id);
            List<Map.Entry <Integer, Double>> list = new LinkedList<>(qwe.entrySet());
            Comparator<Map.Entry<Integer, Double>> byMapValues = new Comparator<Map.Entry<Integer, Double>>() {
                @Override
                public int compare(Map.Entry<Integer, Double> left, Map.Entry<Integer, Double> right) {
                    return -left.getValue().compareTo(right.getValue());
                }
            };
            Collections.sort(list, byMapValues);
            PrintWriter ufoutput = null;
            try
            {

                FileWriter uoutput = new FileWriter(result_file,true);
                ufoutput = new PrintWriter(uoutput);
                int count=1;
                for(Map.Entry< Integer,Double > entry : list)
                {
                    if(count<=file_count)
                    {
                        ufoutput.println( i + " Q0 " + entry.getKey() + " " + count + " " + entry.getValue() + " Kartik_Chanana");
                        count++;
                    }
                }
                ufoutput.close();
                uoutput.close();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }
}
