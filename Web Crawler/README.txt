****README****

* The crawler crawls the pages from a seed URL, to either a depth of 5 or to a limit of 1000 pages.
* Pages with URL already crawled are not added to the to_be_crawled list to avoid duplicacy.
* The Crawler has been created in netbeans 7.2, and hence I would advise compiling it through Netbeans.
* I have used "JSOUP" library downloaded from the 'www.jsoup.org' page.
* To run the project kindly add jsoup.jar(present in the zip file).
* I have attached two word files along '1000 links' and '33 links' that cotain the URL's obtained with non-focused and focused-crawling respectively.
* My code logic finds 33 relevant pages where the word 'cocordance' was found and hence the proportion is 3.3%((33/1000)*100).
* The zip file contains:Project folder(Crawler),the two word files for the URL list,jsoup.jar.
* The code is present at Crawler/src/crawler wherein 'Crawler_main' is the main code to be run. The other java files are intermediate classes
