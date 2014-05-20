
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.*;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import java.util.*;

/** Simple command-line based search demo. */
public class LuceneSearch {
 
  private static String m_index = "LuceneMiniWikiIndex";
  private LuceneSearch() {}

  /** Simple command-line based search demo. */
  public static List<SearchEntry> search(String phrase, String field, int hitsPerPage){
	  System.out.println("Searching for: " + field);	 
	  List<SearchEntry> ret = new ArrayList<SearchEntry>();
	  try{
	    IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(m_index)));
	    IndexSearcher searcher = new IndexSearcher(reader);
	    Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
	    
	    QueryParser parser = new QueryParser(Version.LUCENE_40, field, analyzer);
	    
	    Query query = parser.parse(phrase);
	    System.out.println("Searching for: " + query.toString(field));	 
	   
	    searcher.search(query, null, hitsPerPage);
	    
	    TopDocs results = searcher.search(query,  hitsPerPage);
	    ScoreDoc[] hits = results.scoreDocs;
	    
	    for (ScoreDoc hit : hits){
	        Document doc = searcher.doc(hit.doc);
	        SearchEntry se = new SearchEntry();
	        //String path = doc.get("path");	
	        String title = doc.get("title");
	        se.setTitle(title);
	        se.setScore(hit.score);
	        System.out.println(hit.score+" -"+title);
	        ret.add(se);
	    }
	   reader.close();
	  }catch (IOException e){
		  System.out.println(e.getMessage());
	  }
	  catch (ParseException e){
		  System.out.println(e.getMessage());		  
	  }
	  return ret;
  }
  public static void main(String[] args) throws Exception {
	  
	  //Test searching for bing and lucene with output print.
	  search("Warsaw", "text", 5);
	  BingSearch.search("Warsaw", "title", 5);
	  JQuestions jq = new JQuestions();
	  jq.shuffle(10);
  }

  /**
   * This demonstrates a typical paging search scenario, where the search engine presents 
   * pages of size n to the user. The user can then go to the next page if interested in
   * the next hits.
   * 
   * When the query is executed for the first time, then only enough results are collected
   * to fill 5 result pages. If the user wants to page beyond this limit, then the query
   * is executed another time and all hits are collected.
   * 
   */

}
