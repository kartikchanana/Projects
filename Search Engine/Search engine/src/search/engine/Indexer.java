/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Kartik
 */
public class Indexer {

    HashMap<String, HashMap<Integer, Integer>> tf = new HashMap<>();
    HashMap<String, List<String>> query_map = new HashMap<>();
    List<String> query_id = new ArrayList<String>();
    HashMap<Integer, Integer> doc_tokens = new HashMap<>();
    
    int token_count;
    float avdl=0;
    int total_tokens=0;
    int docount=0;
    public Indexer( String input,String index)
    {
        File index_file= new File(index);
        
        String file1  = input;
        File file = new File(file1);
        String[] list = new String[10000];
        
        
        try
        {
            Scanner sc = new Scanner(file);
            int docid=0;
            
            
            while(sc.hasNextLine())
            {
                String ab= sc.nextLine();
                list=ab.split(" ");
                if(list[0].equals("#"))
                {
                    docid= Integer.parseInt(list[1]);
                    int doc_id_to_store = docid-1;
                    int token_to_store = token_count-1;
                    doc_tokens.put(doc_id_to_store, token_to_store);
                    docount++;
                    token_count = 0;
                    
                }
                int len=list.length;
                
                for (int i=0; i<len ; i++)
                {
                    String word=list[i];
                    int flag=0;
                    for(int j=0 ; j<word.length(); j++)
                    {
                        char ch = word.charAt(j);
                        if(Character.isDigit(ch))
                        {
                            flag=0;
                        }
                        else
                        {
                            flag=1;
                            break;
                            
                        }
                    }
                    if(flag==1)
                    {
                        token_count++;
                        total_tokens++;
                        if(tf.containsKey(word))
                        {
                            HashMap valmap=tf.get(word);
                            if(valmap.containsKey(docid))
                            {
                                int count= (int) valmap.get(docid);
                                HashMap<Integer, Integer> newmap= new HashMap<>();
                                count+= 1;
                                valmap.put(docid, count);
                                tf.put(word, valmap);
                            }
                            else
                            {
                                HashMap<Integer, Integer> newmap= new HashMap<>();
                                valmap.put(docid, 1);
                                tf.put(word, valmap);
                            }
                        }
                        else
                        {
                            HashMap<Integer, Integer> newmap= new HashMap<>();
                            newmap.put(docid, 1);
                            tf.put(word, newmap);
                        }
                    } 
                } 
                if(!sc.hasNextLine())
                {
                    doc_tokens.put(docid, (token_count-1) );
                }
            }
            String hash="#";
            tf.remove(hash);
        total_tokens-=docid;
        
        avdl=(float) total_tokens/docid;
        doc_tokens.remove(0);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        PrintWriter ufoutput = null;
        
        try
        {
            
            FileWriter uoutput = new FileWriter(index_file,true);
            ufoutput = new PrintWriter(uoutput);
            ufoutput.println(tf);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
}
