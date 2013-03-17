package nepal.dina.babylon.data;

import java.util.ArrayList;

public class QuestionGenerator {
	
	
	// TODO dodati language i level i game :) i number
	public static ArrayList<Question> getQuestions(){
		
		ArrayList<Question> ret = new ArrayList<Question>();
		for(int i = 0;i < 10; i++){
			ret.add(new Question("q" + i, "a1 " + i, "a2 " + i, "a3 " + i));
		}
		return ret;
	}

}
