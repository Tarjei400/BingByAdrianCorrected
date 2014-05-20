import java.util.*;
public class TestSet {
	
	public static void main(String[] arg){
		try{
		 JQuestions jq = new JQuestions();
		 List<QuestionType> randomQuestions = jq.shuffle(3);
		 for(QuestionType q : randomQuestions){
			List<SearchEntry> search = LuceneSearch.search(q.getQuestion(), "title", 200);
			System.out.println("Question:"+q.getQuestion());
			System.out.println("Outputs : "+search.size()+" "+q.getQuestion());
			List<SearchEntry> validationSet = BingSearch.search(q.getQuestion(), "title", 10);
			int BingRank = 0;
			for (SearchEntry lse : search){
				String CA = lse.getTitle();
				int bingRank = 0;
				int i = 0;
				for (SearchEntry se : validationSet){
					i++;
					String bingA = se.getTitle();
					//System.out.println("Bing Title:"+bingA+"|| Lucene: "+CA+" Correct: "+q.getAnswer());
					
						bingRank = CA.indexOf(bingA);
				}
				System.out.println(" Lucene: "+CA+" Correct: "+q.getAnswer());
				
				//System.out.println("BR : "+bingRank+" "+lse.getScore()+" "+q.getQuestion());
			}

		 }
		 
		 
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
}
