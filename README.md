NLP Class  
=====================
IBM Watson inspired, Jeperdy question/answering program.
--------------
###Features
 *Giving answer to question taken from Jeperdy.*

###Dependencies
 *-Lucene*
 
 *-WEKA*
 
 *-OpenCSV*
 
 *-Http Client*

###Usage
 *Download miniwikipedia dump.

 *Run MiniWikiParser (It will generarte miniwiki in trec format)
 
 *Next step is to index miniwipedia using Lucene indexer.
 
 *Use Lucene and Bing searchers to look for data.
 
 *Some additional things you need to perform in order to make all pieces work:*
 
	-Add Data folder next to project folder 
	
	-Put miniwiki.xml so it looks like below.
	
	(Directory Should look like this)
	
	..
	/BingByAdrianCorrected
	/Data
	/miniwiki.xml
	
 **More is comming soon.

## TODO:
  *Merge all components, so it ususe both searchers to give proper answer for certain question.*
  
  *Add WEKA Machine learning. (Work but not very efficient)*
  
  *Add OpenNLP support.

 
