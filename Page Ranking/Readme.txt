***************************README***************************

* This project is a replica of a page ranking algorithm used by most web crawlers as a basic functionality.
* The value of page rank is calculated iteratively by considering inlinks,outlinks, and sink nodes for every page in the hierarchy.
* Pages with higher inlink count are not certainly important pages as the content on the page also matters, 
* The PAGE RANKING ALGORITHM has been created in netbeans 7.2, and hence I would advise compiling it through Netbeans.
* No external library has been used.
* The code can be compiled by Java version 7 & and above.
* I have provided two seperate codes for the page rank and the perplexity values calculation.
* There following is the description of the files included in the zip file:
    
    -'top 10 page rank,inlink pages.txt'     : Includes 10 pages with highest page rank & inlink values, & the ANALYSIS for the page rank values obtained.
    -'Top 50 ranked, inlink count pages.txt' : Includes 50 pages with highest page rank & inlink values
    -'perplexity values.txt'                 : Includes perplexity values till the position where it converges.
    -'Proportions.txt'                       : Includes proportion of pages-
                                                                            *With no inlinks
                                                                            *With out inlinks
                                                                            *With whose page rank is less than initial values

    -'Page Ranking'-This is the project including the source code for the algorithm.
                    Source code location-'Page ranking\src\page\ranking'
                    * Compile/run "PageRanking" to obtain teh perplexity convergence and values.
                    * Compile/run "pageranking1" to obtain the ID and page rank values of the inlinks file.
                    * In the main function I have provided the link for the text file that contains the inlinks.
                    * To run it on a different file, kindly change the file name in the code.
                    * Incase on a different machine it is not able to trace the file,kindly copy the "wt2g_inlinks.txt"(present in the zip file) to the project folder.
    -'Page rank 183811.txt'-Includes page rank values for all the pages in the inlinks file provided.
    -'wt2g_inlinks.txt'-Includes the inlinks to run the code.
    -'PAGE RANK-sample.docx'- Includes the page ranks for sample graph after 1,10 and 100 iterations respectively.