/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page.ranking;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Math.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karti
 */
public class pageranking1 {
    public HashMap<String, Float> PR = new HashMap<>();
    public HashMap linkpagecount;
    public void rank(String args)
    {
        check aa= new check();
        aa.getmap(args);
        HashSet allpages=aa.allpages;
        String[] allpages1= new String[allpages.size()];
        allpages.toArray(allpages1);
        
        HashSet lesspagerank = new HashSet();
        HashSet noinlinks = aa.noinlinks;
        HashSet sinknodes=aa.sinknodes;
        String[] sn= new String[sinknodes.size()];
        sinknodes.toArray(sn);
        HashMap linkpages=aa.linkpage;
        linkpagecount=aa.linkpagecount;
        HashMap outlinks=aa.outlink;
        
        HashMap<String, Float> newPR = new HashMap<>();
        List<Float> perplexity = new ArrayList<>();
        int N=allpages.size();
        float initPR= (float) 1/N;
        HashMap<String, ArrayList> Map1 = aa.linkpage;// Total map received here
        Iterator<String> keySetIterator = Map1.keySet().iterator(); 
        //STEP 1
        while(keySetIterator.hasNext())
        { 
            String key = keySetIterator.next();
            ArrayList ap= Map1.get(key);
            PR.put(key, initPR);
        }
        
        Iterator<String> NPRI = newPR.keySet().iterator(); //NewPageRankIterator

        
        //step 2
        int i=0;
        while(i<100)
        {
            float sinkPR=0;
            float probability=0;
            //loop 1
            for(int j=0;j<sinknodes.size();j++)
            {
                String key=sn[j];
                Float value=PR.get(key);
                sinkPR += value;
            }
            
            //loop 2
            for(int w=0;w<allpages1.length;w++)
            {
                String key=allpages1[w];
                float val= (float )((1-0.85)/N);
                val+= (float )((0.85*sinkPR)/N);
                newPR.put(key, val);
                Float newprp1=(float)0;
                ArrayList lp1 = (ArrayList) linkpages.get(key);
                for(int j=0;j<lp1.size();j++)
                {
                    String page1= (String )lp1.get(j);
                    newprp1 = newPR.get(key);
                    Float prq= PR.get(page1);
                    Integer lq1= (Integer) outlinks.get(page1);
                    newprp1+= (float)((0.85*prq)/lq1);
                    newPR.put(key, newprp1);
                }
                probability+=newprp1;
            }
            //loop 4-passing values
            float entropyval=0;
            for(int j=0;j<allpages1.length;j++)
            {
                String pagee=allpages1[j];
                Float rank=newPR.get(pagee);
                PR.remove(pagee);
                PR.put(pagee, rank);
            }
            i++;
        }
        Iterator<String> PRI = PR.keySet().iterator(); //PageRankIterator
        for(int y=0;y<PR.size();y++)
        {
            String key=PRI.next();
            float value=PR.get(key);
            System.out.println("ID:" + key + " Rank :" + value);
            if(value<initPR)
            {
                lesspagerank.add(key);
            }
        }
        
        
        
    }
    public static void main(String[] args)
    {
        pageranking1 pa=new pageranking1();
        pa.rank("wt2g_inlinks.txt");
    }
}
