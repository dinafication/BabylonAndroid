package nepal.dina.babylon;

public class ResourceMapper {
	
	public enum Resource{
		LNG(0,66, R.drawable.lng2, R.drawable.lng2), LNG_GER(0,0, R.drawable.lng2_ger, R.drawable.german_c2), 
		LNG_FR(0,1, R.drawable.lng2_fer, R.drawable.french_c2), LNG_ITA(0,2, R.drawable.lng2_ita, R.drawable.italiah_c2),
		LNG_ESP(0,3, R.drawable.lng2_spa, R.drawable.spanish_c2), 
		LVL(1, 66, R.drawable.lvl3, R.drawable.lvl3), LVL_E(1,0, R.drawable.lvl3e, R.drawable.lvl_ec2),
		LVL_PI(1,1, R.drawable.lvl5pi, R.drawable.lvl_pic2), LVL_I(1,2, R.drawable.lvl5i, R.drawable.lvl_ic2), 
		LVL_UI(1,3, R.drawable.lvl5ui, R.drawable.lvl_uic2), LVL_A(1,4, R.drawable.lvl3a, R.drawable.lvl_ac2);
		
		private int parent;
		private int child;
		private int parentDrawable;
		private int childDrawable;
		
		private Resource(int parent, int child, int parentDrawable, int childDrawable){
			this.parent = parent;
			this.child = child;
			this.parentDrawable = parentDrawable;
			this.childDrawable = childDrawable;
		}
		
		public int getParent() {
			return parent;
		}

		public int getChild() {
			return child;
		}
		
		public int getParentDrawable(){
			return parentDrawable;
		}
		
		public int getChildDrawable(){
			return childDrawable;
		}
		
		
	}


	public static Resource getResource(int parent, int child){
		
		for(Resource r:Resource.values()){
			if(r.getParent() != parent){
				continue;
			}
			if(r.getChild() == child)return r;
		}
		
		return null;
	}
	
	
	
}
