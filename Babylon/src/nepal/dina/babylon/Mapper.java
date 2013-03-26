package nepal.dina.babylon;

import nepal.dina.babylon.expandableList.GroupEntity.GroupItemEntity;

public class Mapper {
	


	public static String getLevel(String level){
		if(level.equals("Elementary"))return "E";
		if(level.equals("Pre-intermediate"))return "PI";
		if(level.equals("Intermediate"))return "I";
		if(level.equals("Upper-intermediate"))return "UI";
		else return "A";
	}
	
	public static String getLevel(int level){
		if(level == 0)return "E";
		if(level == 1)return "PI";
		if(level == 2)return "I";
		if(level == 3)return "UI";
		else return "A";
	}
	
	
	public static String getLanguage(String language){
		if(language.equals("German"))return "ger";
		if(language.equals("French"))return "fra";
		if(language.equals("Italian"))return "tal";
		else return "span";
	}
	public static String getLanguage(int language){
		if(language == 0)return "ger";
		if(language == 1)return "fra";
		if(language == 2)return "tal";
		else return "span";
	}
}
