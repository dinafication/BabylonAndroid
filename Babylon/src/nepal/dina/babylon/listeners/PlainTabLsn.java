package nepal.dina.babylon.listeners;

import java.util.ArrayList;
import java.util.List;

import nepal.dina.babylon.R;
import nepal.dina.babylon.data.Question;
import nepal.dina.babylon.data.QuestionGenerator;
import nepal.dina.babylon.expandableList.GroupEntity;
import nepal.dina.babylon.nexts.CollectionPagerAdapter;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.ExpandableListView;

import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class PlainTabLsn<T extends Fragment> implements TabListener {
	
	 	private Fragment mFragment;
	    private final Activity mActivity;
	    private final String mTag;
	    private final Class<T> mClass;
	    
	  

	    /** Constructor used each time a new tab is created.
	      * @param activity  The host Activity, used to instantiate the fragment
	      * @param tag  The identifier tag for the fragment
	      * @param clz  The fragment's Class, used to instantiate the fragment
	      */
	    public PlainTabLsn(Activity activity, String tag, Class<T> clz) {
	        mActivity = activity;
	        mTag = tag;
	        mClass = clz;
	        
	    }
	    
	    public void switchQuestion(int next){
	    	
	    }

	   
	    public void onTabSelected(Tab tab, FragmentTransaction ft) {
	    	
	    	//mActivity.setContentView(R.layout.fragments_play);
	    	
	    	 
				
		        if (mFragment == null) {
		            // If not, instantiate and add it to the activity
		        	
		            mFragment = Fragment.instantiate(mActivity, mClass.getName());
		            ft.add(android.R.id.content, mFragment, mTag);
		        } else {
		            // If it exists, simply attach it in order to show it
		            ft.attach(mFragment);
		        }
	        
	    }

	    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	        if (mFragment != null) {
	            // Detach the fragment, because another one is being attached
	            ft.detach(mFragment);
	        }
	    }

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

	    

}
