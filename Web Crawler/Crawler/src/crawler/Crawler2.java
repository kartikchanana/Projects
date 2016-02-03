
package crawler;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler2
{
    private List<String> links = new LinkedList<>();
    private Document htmlDocument;


    //url:The link passed by search method to crawl.
    //This is the method that derives all the links on a page.
    public boolean crawl(String url)
    {
        try
        {
            Connection connection;
            connection = Jsoup.connect(url);
            Document html = connection.get();
            this.htmlDocument = html;
            if(connection.response().statusCode() == 200)
            {
                System.out.println("\nVisiting web page: " + url);
            }
            if(!connection.response().contentType().contains("text/html"))
            {
                System.out.println("No html content received");
                return false;
            }
            Elements linkscrawled = htmlDocument.select("a[href]");
            System.out.println("Found (" + linkscrawled.size() + ") links");//Number of pages found(Include all-relevant and non-relevant)
            for(Element link : linkscrawled)
            {
                //if url attained passes al the validations it is ended to the linked list-links
                String attain=link.absUrl("href");
                String posturl;
                if(attain.length()>29)
                {
                    String attain1=link.absUrl("href");//now attain1 contains the WHOLE url,not the last part
                    posturl= attain1.substring(29, attain1.length());//29 letters contain the wikipedia page base URL
                    int posturlindexcolon=posturl.indexOf(":");
                    //Validations
                    if(link.absUrl("href").contains("http://en.wikipedia.org/wiki/") || link.absUrl("href").contains("https://en.wikipedia.org/wiki/") && posturlindexcolon<=0 && !link.absUrl("href").contains("http://en.wikipedia.org/wiki/Main_Page") && !link.absUrl("href").contains("https://en.wikipedia.org/wiki/Main_Page"))
                    {
                        this.links.add(link.absUrl("href"));
                    }
                }

            }
            return true;
        }
        catch(IOException ioe)
        {
            // If the HTTP request fails
            return false;
        }
    }


    //The method called by Crawler classs to search  the keyphrase
    public boolean searchForWord(String searchWord)
    {
        if(this.htmlDocument == null)
        {
            System.out.println("ERROR! Call crawl() before performing analysis on the document");
            return false;
        }
        String bodyText = this.htmlDocument.body().text();
        return bodyText.toLowerCase().contains(searchWord.toLowerCase());
    }

//public method to access the private linked list of all URL's fro Crawler classs.
    public List<String> getLinks()
    {
        return this.links;
    }

}