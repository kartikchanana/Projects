/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package page.ranking;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author karti
 */
public class check {
public HashMap<String, ArrayList> Map = new HashMap<>();
HashSet allpages = new HashSet();
HashSet linkpages = new HashSet();
HashSet nonsinkpages = new HashSet();
HashSet sinknodes = new HashSet();
HashSet noinlinks = new HashSet();
HashMap<String, ArrayList> linkpage = new HashMap<>();
HashMap<String, Integer> linkpagecount = new HashMap<>();
HashMap<String, Integer> outlink = new HashMap<>();

public void getmap(String args)
        {
        String[] s= new String[200000];
        String filelink=args;
        File file = new File(filelink);
        HashSet S= new HashSet();
        try
        {
            Scanner sc= new Scanner(file);
            
            
            while(sc.hasNextLine())
            {
                System.out.println("Doing while");
                ArrayList list= new ArrayList();
                s=sc.nextLine().split(" ");
                //for s[0]
                if(outlink.containsKey(s[0]))
                {
                    int val=outlink.get(s[0]);
                    int newval=val+1;
                    outlink.remove(s[0]);
                    outlink.put(s[0], newval);
                }
                else{outlink.put(s[0], 0);}
                S.add(s[0]);
                allpages.add(s[0]);
                for(int i=1;i<s.length;i++)
                    {
                        //for s[i]
                        nonsinkpages.add(s[i]);
                        if(outlink.containsKey(s[i]))
                        {
                            int val=outlink.get(s[i]);
                            int newval=val+1;
                            outlink.remove(s[i]);
                            outlink.put(s[i], newval);
                        }
                        else{outlink.put(s[i], 0);}
                        list.add(s[i]);
                        allpages.add(s[i]);
                    }
            linkpage.put(s[0],list);
            linkpagecount.put(s[0],list.size());
            if(list.size()==0)
            {
                noinlinks.add(s[0]);
            }
            sinknodes.addAll(allpages);
            sinknodes.removeAll(nonsinkpages);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }        
    }
}
