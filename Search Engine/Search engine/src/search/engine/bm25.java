/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search.engine;

import java.io.File;
import java.util.*;

/**
 *
 * @author Kartik
 */
public class bm25 {
 
    
    
    public bm25(String index, String query, int files, String result)
    {
        Indexer se = new Indexer("tccorpus.txt", index);
        File query_file= new File(query);
        String[] terms = new String[10000];
        String[] terms1=new String[1000];
        HashMap<Integer, HashMap<String, Integer>> queries_map = new HashMap<>(); 
        try
        {
            Scanner query_ip=new Scanner(query_file);
            int query_id=1;
            while(query_ip.hasNextLine())
            {
                HashMap<String, Integer> query_freq = new HashMap<>();
                List<String> query_terms = new ArrayList<String>();
                String qry = query_ip.nextLine();
                terms1=qry.split(" ");
                int len1=terms1.length;
                for( int j=0;j<len1;j++ )
                {
                    String word= terms1[j];
                    if(query_freq.containsKey(word))
                    {
                        int freq = query_freq.get(word);
                        query_freq.put(word, (freq+1) );
                    }
                    else
                    {
                        query_freq.put(word, 1);
                    }
                }
                queries_map.put(query_id, query_freq);
                query_id++;
                
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        HashMap<Integer, HashMap<String, Integer>> query_map = queries_map;
        HashMap<String, HashMap<Integer, Integer>> tf = se.tf;
        HashMap<Integer, Integer> doc_tokens = se.doc_tokens;
        List<String> query_id = se.query_id;
        
        HashMap<String, HashMap<Integer, Double>> query_score = new HashMap<>();
        int N=se.docount;
        float avdl=se.avdl;
        int token_count=se.total_tokens;
        double k1=1.2;
        double k2=100;
        double b=0.75;
        double ex1= (1-b);
        for (int i=0;i<query_map.size();i++)// iterating over queries
        {
            String queryid="Q"+ Integer.toString(i);
            HashMap<String, Integer> current = new HashMap<>();
            current=query_map.get(i+1);
            Set<String> abc = current.keySet();
            List<String> query_words = new ArrayList<>(abc);//List of words in query one
            HashMap<Integer, Double> word_score = new HashMap<>();
            for(int j=0;j<query_words.size();j++)
            {
                String current1= query_words.get(j);
                
                if(tf.containsKey(current1))
                {
                    HashMap<Integer, Integer> data = new HashMap<>();
                    data = tf.get(current1);
                    Set <Integer> docs = data.keySet();
                    List<Integer> doc = new ArrayList<>(docs);//list of documents 
                    for(int k=0 ; k< doc.size() ; k++)
                    {
                        int doc_number=doc.get(k);
                        int fi = data.get(doc_number);
                        int ni=data.size();
                        int qfi=current.get(current1);
                        int dl = doc_tokens.get(doc_number);
                        //calculating K
                        double ex2=(b* dl);
                        double ex3= ex2/avdl;
                        double K= (k1 * ( (1-b) + ex3));
                        double term1 = ( (N-ni+0.5) / (ni + 0.5));
                        double term2 = (( k1 + 1 ) * fi / ( K + fi ) );
                        double term3 = ( (( k2+1 ) * qfi ) / ( k2 + qfi ) );
                        double doc_score1 = Math.log(term1);
                        double doc_score=doc_score1*term2*term3;
                        if(word_score.containsKey(doc_number))
                        {
                            double value= word_score.get(doc_number);
                            value+=doc_score;
                            word_score.put(doc_number, value);
                        }
                        else
                        {
                            word_score.put(doc_number, doc_score);
                        }
                    }
                }
            }
         query_score.put(queryid, word_score);
        }
        Sort sort_it= new Sort();
        sort_it.Sort(files, query_score, result);
    }
    
    
    public static void main(String args [])
    {
        bm25 bm=new bm25("index.out.txt","queries.txt",100,"results.eval.txt");
    }
}
