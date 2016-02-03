
package crawler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Crawler
{
  private static final int MaxSearchLimit = 1000;
  private Set<String> pagesVisited = new HashSet<>();
  private List<String> pagesToVisit = new LinkedList<>();
  List<String> x = new ArrayList<>();
  String wordfoundat[]=new String[100];


  //URL:Seed page provided initially
  //searchWord:Keyphrase to be found
  //depth:The depth to which URL's are to be crawled
  public void search(String url, String searchWord, int depth)
  {
      int successcount=0;
      while(this.pagesVisited.size() < MaxSearchLimit)
      {    
          String currentUrl;
          Crawler2 leg = new Crawler2(); 
          for(int i=0;i<depth-1;i++)
          {
          if(this.pagesToVisit.isEmpty())
          {
              currentUrl = url;
              this.pagesVisited.add(url);
          }
          else
          {
              currentUrl = this.nextUrl();
          }
          
          leg.crawl(currentUrl); 
          boolean wordfound = leg.searchForWord(searchWord);
          if(wordfound)
          {
              System.out.println(String.format("Word " +searchWord+  " found at " + currentUrl));
              x.add(currentUrl);
              successcount++; 
          }
          System.out.println(successcount);
          this.pagesToVisit.addAll(leg.getLinks());
          }
      }
      System.out.println("\n**Done** Visited " + this.pagesVisited.size() + " web page(s)");
      
      String allurl=this.pagesVisited.toString();
      String a[]=allurl.split(",");
      for(int r=0;r<1040;r++)
      {
          System.out.println("All URL's found(1000)" + a[r]);//List of  1000 URL's
      }
      String wordfoundurl=x.toString();
      String abc[]=wordfoundurl.split(",");
      for(int r=0;r<abc.length;r++)
      {
          System.out.println("With keyphrase URL;s found" + abc[r]);//List of URL's where the word is found
      }
  }

//Gives the next URL in the to-visit link
  private String nextUrl()
  {
      String nextUrl;
      do
      {
          nextUrl = this.pagesToVisit.remove(0);
      } while(this.pagesVisited.contains(nextUrl));
      this.pagesVisited.add(nextUrl);
      return nextUrl;
  }
}