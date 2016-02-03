/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lucene;

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
    
    public void Sort (HashMap terms) 
    {
        HashMap<String, Long> term_frq = terms;
        
        List<Map.Entry <String, Long>> list = new LinkedList<>(term_frq.entrySet());
        Comparator<Map.Entry<String, Long>> byMapValues = new Comparator<Map.Entry<String, Long>>() 
        {
            @Override
            public int compare(Map.Entry<String, Long> left, Map.Entry<String, Long> right) {
                return -left.getValue().compareTo(right.getValue());
            }
        };
        Collections.sort(list, byMapValues);
        PrintWriter ufoutput = null;
        PrintWriter ufoutput1 = null;
        PrintWriter ufoutput2 = null;
        try
            {
                File result_file = new File("termsfrequency1.txt");
                File justkey = new File("terms1.txt");
                File justval = new File("vals1.txt");
                FileWriter uoutput = new FileWriter(result_file,true);
                FileWriter uoutput1 = new FileWriter(justkey,true);
                FileWriter uoutput2 = new FileWriter(justval,true);
                ufoutput = new PrintWriter(uoutput);
                ufoutput1 = new PrintWriter(uoutput1);
                ufoutput2 = new PrintWriter(uoutput2);
                int count=1;
                for(Map.Entry<String, Long> entry : list)
                {
                    if(count<=term_frq.size())
                    {
                        ufoutput.println(entry.getKey() + "  " + entry.getValue());
                        ufoutput1.println(entry.getKey());
                        ufoutput2.println(entry.getValue());
                        count++;
                    }
                }
                ufoutput.close();
                ufoutput2.close();
                ufoutput1.close();
                uoutput.close();
                uoutput1.close();
                uoutput2.close();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }

