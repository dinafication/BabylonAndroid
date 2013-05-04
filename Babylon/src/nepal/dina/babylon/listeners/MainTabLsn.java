package nepal.dina.babylon.listeners;

import nepal.dina.babylon.play.PlayFragment;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;

public class MainTabLsn<T extends Fragment> implements TabListener{

	 private Fragment mFragment;
	    private final Activity mActivity;
	    private final String mTag;
	    private final Class<T> mClass;
	    
	    private PlayFragment mFragmentPly;
	    private Fragment selected;
	    
	    public Fragment getSelectedfragment(){
	    	return selected;
	    }

	    /** Constructor used each time a new tab is created.
	      * @param activity  The host Activity, used to instantiate the fragment
	      * @param tag  The identifier tag for the fragment
	      * @param clz  The fragment's Class, used to instantiate the fragment
	      */
	    public MainTabLsn(Activity activity, String tag, Class<T> clz) {
	        mActivity = activity;
	        mTag = tag;
	        mClass = clz;
	    }

	    /* The following are each of the ActionBar.TabListener callbacks */

	    public void onTabSelected(Tab tab, FragmentTransaction ft) {
	    	
	    	// home
	    	if(selected == null || (selected.equals(mFragment))){
	    		onHome(tab, ft);
	    	}
	    	// play
	    	else{
	    		onPlay(tab, ft);
	    	}
	       
	    }

	    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	        if (mFragment != null) {
	            // Detach the fragment, because another one is being attached
	            ft.detach(mFragment);
	        }
	        if (mFragmentPly != null) {
	            // Detach the fragment, because another one is being attached
	            ft.detach(mFragmentPly);
	        }
	    }

	    public void onTabReselected(Tab tab, FragmentTransaction ft) {
	        onHome(tab, ft);
	    }
	    
	    public void onHome(Tab tab, FragmentTransaction ft){
	    	if (mFragmentPly != null) {
	            // Detach the fragment, because another one is being attached
	            ft.detach(mFragmentPly);
	        }
    		 // Check if the fragment is already initialized
	        if (mFragment == null) {
	            // If not, instantiate and add it to the activity
	            mFragment = Fragment.instantiate(mActivity, mClass.getName());
	            ft.add(android.R.id.content, mFragment, mTag);
	        } else {
	            // If it exists, simply attach it in order to show it
	            ft.attach(mFragment);
	        }
	        selected = mFragment;
	    }
	    
	    public void onPlay(Tab tab, FragmentTransaction ft) {
	    	if (mFragment != null) {
	            // Detach the fragment, because another one is being attached
	            ft.detach(mFragment);
	        }
	    	
	        // Check if the fragment is already initialized
	        if (mFragmentPly == null) {
	        	
	        	
	            // If not, instantiate and add it to the activity
	        	mFragmentPly = (PlayFragment) Fragment.instantiate(mActivity, PlayFragment.class.getName());
	            ft.add(android.R.id.content, mFragmentPly, mTag);
	        } else {
	            // If it exists, simply attach it in order to show it
	            ft.attach(mFragmentPly);
	        }
	        selected = mFragmentPly;
	    }

}
