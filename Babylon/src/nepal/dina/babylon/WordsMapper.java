package nepal.dina.babylon;

public class WordsMapper {
	
	public static String getGroupName(String lng, String lvl){
		
		StringBuffer sb = new StringBuffer();
		
		if(lng.equals("ger"))sb.append("GERMAN");
		if(lng.equals("tal"))sb.append("ITALIAN");
		if(lng.equals("span"))sb.append("SPANISH");
		if(lng.equals("fra"))sb.append("FRENCH");
		
		sb.append(" - ");
		
		if(lvl.equals("A"))sb.append("advanced");
		if(lvl.equals("E"))sb.append("elementary");
		if(lvl.equals("I"))sb.append("intermediate");
		if(lvl.equals("UI"))sb.append("upper-inter.");
		if(lvl.equals("PI"))sb.append("pre-inter");
		
		
		return sb.toString();
	}

}
