package nepal.dina.babylon.data;

public class Question {
	
	private String q, a1, a2, a3;
	
	public Question(String q, String a1, String a2, String a3){
		
		this.q = q;
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
	}
	
	public String getQ(){
		return q;
	}

	public String getA1(){
		return a1;
	}
	public String getA2(){
		return a2;
	}
	public String getA3(){
		return a3;
	}
}
