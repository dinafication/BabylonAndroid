package nepal.dina.babylon;

public class Mapper {

	public static String getLevel(int indx){
		if(indx == 0)return "E";
		if(indx == 1)return "PI";
		if(indx == 2)return "I";
		if(indx == 2)return "UI";
		else return "A";
	}
	
	public static String getLanguage(int indx){
		if(indx == 0)return "ger";
		if(indx == 1)return "fra";
		if(indx == 2)return "tal";
		else return "span";
	}
}
