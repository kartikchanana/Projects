***************************README***************************

* The search engine uses lucene to perform basic stemming and indexing and then stores the tokens.
* The query is then fetched from the file 'queries.txt' and results are fetched on the basis of scores calculated using BM25 formula.
* The top ranked pages are displayed with a small snippet(200 characters) containing the relevant text for the query.
* A Zipf's law curve is drawn on the basis of results observed ("Final Output.xlsx")
* The LUCENE project has been created in netbeans 7.2, and hence I would advise compiling it through Netbeans.
* Jsoup library has been used for parsing the documents
* The code can be compiled by Java version 7 & and above.
* The main file that has to be executed is "lucene.java" to obtain the results.
* You will have to interact at the console to provide the directory to create the index and to pickup files from a directory to create the index as well
* I have insluded all the cacm files in a folder "lucene"
* I am including in the zip file, the netbeans project folder that contains the code and the output files, and a readme file.
* the excel sheet "final output" contains the data and the graphs of Zipf's law
* Query_results1,Query_results2,Query_results3,Query_results4 contain the results of top 100 documents received for the queries along with snippet of 200 characters as well.
* the file "termsfrequency1" contains the list of unique terms found and thier frequencies.