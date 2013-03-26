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
	
	public static String getLanguage(String language){
		if(language.equals("German"))return "ger";
		if(language.equals("French"))return "fra";
		if(language.equals("Italian"))return "tal";
		else return "span";
	}
}
