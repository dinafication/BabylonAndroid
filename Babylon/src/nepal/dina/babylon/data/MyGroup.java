package nepal.dina.babylon.data;

import java.util.ArrayList;

import nepal.dina.babylon.WordsMapper;

public class MyGroup {

	
	private String id;
	private String name;
	
	
	private String lng;
	private String lvl;
	

	private ArrayList<Pair<String, String>> words;
	
	public MyGroup(String id, String lng, String lvl) {
		this.id = id;
		this.lvl = lvl;
		this.lng=  lng;
		
		this.name = WordsMapper.getGroupName(lng, lvl);
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lng == null) ? 0 : lng.hashCode());
		result = prime * result + ((lvl == null) ? 0 : lvl.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyGroup other = (MyGroup) obj;
		if (lng == null) {
			if (other.lng != null)
				return false;
		} else if (!lng.equals(other.lng))
			return false;
		if (lvl == null) {
			if (other.lvl != null)
				return false;
		} else if (!lvl.equals(other.lvl))
			return false;
		return true;
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
