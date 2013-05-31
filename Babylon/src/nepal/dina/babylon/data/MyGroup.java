package nepal.dina.babylon.data;

import java.util.ArrayList;

public class MyGroup {

	
	private String id;
	private String name;
	
	

	private ArrayList<Pair<String, String>> words;
	
	public MyGroup(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	public ArrayList<Pair<String, String>> getWords() {
		return words;
	}

	public void setWords(ArrayList<Pair<String, String>> words) {
		this.words = words;
	}

	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
}
