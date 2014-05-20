import java.util.*;
public class TestSet {
	
	public static void main(String[] arg){
		try{
		 JQuestions jq = new JQuestions();
		 List<QuestionType> randomQuestions = jq.shuffle(100);
		 int amount = 0;
		 int bingamount = 0;
		 int j = 0;
		 for(QuestionType q : randomQuestions){
			List<SearchEntry> search = LuceneSearch.search(q.getQuestion(), "text", 200);

			List<SearchEntry> validationSet = BingSearch.search(q.getQuestion(), "title", 100);
			int BingRank = 0;
			j++;
			for (SearchEntry lse : search){
			
				String CA = lse.getTitle().toLowerCase();
				int bingRank = 0;
				int i = 0;
				for (SearchEntry se : validationSet){
					i++;
					String bingA = se.getTitle().toLowerCase();
					//System.out.println("Bing Title:"+bingA+"|| Lucene: "+CA+" Correct: "+q.getAnswer());
					if (CA.indexOf(bingA) > 0 || bingA.indexOf(CA) > 0){
						bingRank = i;
						break;
					}
				}
				if(CA.indexOf(q.getAnswer().toLowerCase()) > 0){
					System.out.println("Question:"+q.getQuestion());
					System.out.println("Outputs : "+search.size()+" "+q.getQuestion());	
					System.out.println("Answer : "+q.getAnswer());	
					System.out.println("CA : "+CA);	
					System.out.println("BingRank : "+bingRank);	
					amount++;
					break;
				}

			
				
				//System.out.println("BR : "+bingRank+" "+lse.getScore()+" "+q.getQuestion());
			}

			 System.out.println(" which question: "+j);

		 }
		 System.out.println(" Lucene: "+amount);
		 System.out.println(" Bing: "+amount);		 
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
}
