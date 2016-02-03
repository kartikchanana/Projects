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
public class PageRanking {
    public void rank(String abc)
    {
        check aa= new check();
        aa.getmap(abc);
        HashSet allpages=aa.allpages;
        
        String[] allpages1= new String[allpages.size()];
        allpages.toArray(allpages1);

        HashSet sinknodes=aa.sinknodes;
        String[] sn= new String[sinknodes.size()];
        sinknodes.toArray(sn);
        HashMap linkpages=aa.linkpage;
        HashMap outlinks=aa.outlink;
        HashMap<String, Float> PR = new HashMap<>();
        //TreeSet<String > nameComp = new TreeSet<String>();
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
            String[] ab=new String[ap.size()];
            ap.toArray(ab);
            for(int i=0;i<ap.size();i++)
            {
                PR.put(ab[i], initPR);
            }
            PR.put(key, initPR);
        }
        Iterator<String> PRI = PR.keySet().iterator(); //PageRankIterator
        Iterator<String> NPRI = newPR.keySet().iterator(); //NewPageRankIterator

        
        //step 2
        boolean converged=false;
        int counter=0;
        while(!converged)
        {
            counter++;
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
                
                //************************
                
                float ev=(float)(Math.log(1/rank) / (Math.log(2)));
                float ev1=rank*ev;
                entropyval+= ev1;
            }
                float perplexity1 = (float) Math.pow(2,entropyval);
                    perplexity.add(perplexity1);
                    
                        int a=perplexity.size();
                        if(perplexity.size() >=4)
                        {
                            float val1=perplexity.get(a-1);
                            float val2=perplexity.get(a-2);
                            float val3=perplexity.get(a-3);
                            float val4=perplexity.get(a-4);
                            if(((Math.abs(val1-val2)) < 1) && ((Math.abs(val2-val3)) < 1) && ((Math.abs(val3-val4)) < 1))
                            {
                                System.out.println("Converged at : " + (counter));
                                System.out.println("perplexity value: " + perplexity1);
                                converged=true;
                                break;
                            }
                        }
                        System.out.println("Perplexity at " + counter  + ": "  + perplexity1);
                    
            }
                
    }
    public static void main(String[] a)
    {
        PageRanking pa=new PageRanking();
        String link="wt2g_inlinks.txt";
        pa.rank(link);
    }
}
