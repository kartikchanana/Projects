package crawler;

public class Crawlertest
{

    public static void main(String[] args)
    {
        Crawler crawler = new Crawler();
        crawler.search("http://en.wikipedia.org/wiki/Hugh_of_Saint-Cher", "concordance", 5);
    }
}