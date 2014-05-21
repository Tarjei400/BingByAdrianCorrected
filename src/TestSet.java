import java.util.*;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.*;

public class TestSet {
	
	public static void main(String[] arg){
		try{
		 JQuestions jq = new JQuestions();
		 List<QuestionType> randomQuestions = jq.shuffle(1000);
		 int amount = 0;
		 int bingamount = 0;
		
		 BufferedWriter bf = new BufferedWriter(new FileWriter("TrainSet.csv"));
		 CSVWriter csvw = new CSVWriter(bf);
		 for(QuestionType q : randomQuestions){
			List<SearchEntry> search = LuceneSearch.search(q.getQuestion(), "text", 10);

			List<SearchEntry> validationSet = BingSearch.search(q.getQuestion(), "title", 10);
			int BingRank = 0;
			 int j = 0;
			for (SearchEntry lse : search){
				j++;
				String CA = lse.getTitle().toLowerCase();
				String bingRank = "?";
				int i = 0;
				for (SearchEntry se : validationSet){
					i++;
					String bingA = se.getTitle().toLowerCase();
					//System.out.println("Bing Title:"+bingA+"|| Lucene: "+CA+" Correct: "+q.getAnswer());
					if (CA.indexOf(bingA) > 0 || bingA.indexOf(CA) > 0){
						bingRank = ""+i;
						break;
					}
				}
				int isTrue = 0;
				if(CA.indexOf(q.getAnswer().toLowerCase()) > 0){
					System.out.println("Question:"+q.getQuestion());
					System.out.println("Outputs : "+search.size()+" "+q.getQuestion());	
					System.out.println("Answer : "+q.getAnswer());	
					System.out.println("CA : "+CA);	
					System.out.println("BingRank : "+bingRank);	
					amount++;
					isTrue = 1;
					
				}
				String[] trainRow = {""+j, bingRank, ""+lse.getScore(), ""+isTrue};
				csvw.writeNext(trainRow);
			
				
				//System.out.println("BR : "+bingRank+" "+lse.getScore()+" "+q.getQuestion());
			}

			 System.out.println(" which question: "+j);

		 }
			bf.close();
		 System.out.println(" Lucene: "+amount);
		 System.out.println(" Bing: "+amount);		 
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
}
