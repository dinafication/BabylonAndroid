package nepal.dina.babylon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nepal.dina.babylon.data.DataBaseHelper;
import nepal.dina.babylon.expandableList.ExpandableListAdapter;
import nepal.dina.babylon.expandableList.GroupEntity;
import nepal.dina.babylon.expandableList.GroupEntity.GroupItemEntity;
import nepal.dina.babylon.nexts.ObjectFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ExpandableListView;
import android.widget.TextView;

import android.content.Context;
import android.database.SQLException;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MainFragment extends Fragment{
	
	public ExpandableListView mExpandableListView;
	public ExpandableListAdapter adapter;
	private List<GroupEntity> mGroupCollection;
	
	 View view;

	 private int  lastExpandedGroupIndx  = 100;
	 
	 
	 private GroupEntity lng;
	 
	 public String language;
	 
	 
	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
		  
		 
		  
		  
	    view = inflater.inflate(R.layout.fragment_main,
	        container, false);
	    getActivity().setContentView(R.layout.main);
	    
	    
	    prepareSettingsResource();
		 initSettingsPage();
	    return view;
	  }

	  private void prepareSettingsResource() {

			mGroupCollection = new ArrayList<GroupEntity>();

			// for (int i = 1; i < 6; i++) {
			// GroupEntity ge = new GroupEntity();
			// ge.Name = "Group" + i;
			//
			// for (int j = 1; j < 5; j++) {
			// GroupItemEntity gi = ge.new GroupItemEntity();
			// gi.Name = "Child" + j;
			// ge.GroupItemCollection.add(gi);
			// }
			// mGroupCollection.add(ge);
			// }

			// moj jezik
//			GroupEntity myLng = new GroupEntity();
//			myLng.Name = "My language";
//
//			GroupItemEntity eng = myLng.new GroupItemEntity();
//			eng.Name = "German";
//			myLng.GroupItemCollection.add(eng);
//
//			GroupItemEntity ger = myLng.new GroupItemEntity();
//			ger.Name = "Italian";
//			myLng.GroupItemCollection.add(ger);
//
//			GroupItemEntity tal = myLng.new GroupItemEntity();
//			tal.Name = "French";
//			myLng.GroupItemCollection.add(tal);
//
//			mGroupCollection.add(myLng);

			// jezik za ucenje
			lng = new GroupEntity();
			lng.Name = "Language ";

			GroupItemEntity eng2 = lng.new GroupItemEntity();
			eng2.Name = "German";
			lng.GroupItemCollection.add(eng2);

			GroupItemEntity ger2 = lng.new GroupItemEntity();
			ger2.Name = "French";
			lng.GroupItemCollection.add(ger2);

			GroupItemEntity tal2 = lng.new GroupItemEntity();
			tal2.Name = "Italian";
			lng.GroupItemCollection.add(tal2);
			
			GroupItemEntity span = lng.new GroupItemEntity();
			span.Name = "Spanish";
			lng.GroupItemCollection.add(span);

			mGroupCollection.add(lng);

			// game
//			GroupEntity gm = new GroupEntity();
//			gm.Name = "Game";
//
//			GroupItemEntity syn = myLng.new GroupItemEntity();
//			syn.Name = "Synonyms";
//			gm.GroupItemCollection.add(syn);
//
//			GroupItemEntity trn = myLng.new GroupItemEntity();
//			trn.Name = "Translation";
//			gm.GroupItemCollection.add(trn);
//
//			mGroupCollection.add(gm);

			// level
			GroupEntity lvl = new GroupEntity();
			lvl.Name = "Level";

			GroupItemEntity e = lvl.new GroupItemEntity();
			e.Name = "Elementary";
			lvl.GroupItemCollection.add(e);
			
			GroupItemEntity pi = lvl.new GroupItemEntity();
			pi.Name = "Pre-intermediate";
			lvl.GroupItemCollection.add(pi);

			GroupItemEntity i = lvl.new GroupItemEntity();
			i.Name = "Intermediate";
			lvl.GroupItemCollection.add(i);
			
			GroupItemEntity ui = lvl.new GroupItemEntity();
			ui.Name = "Upper-intermediate";
			lvl.GroupItemCollection.add(ui);
			
			GroupItemEntity ad = lvl.new GroupItemEntity();
			ad.Name = "Advanced";
			lvl.GroupItemCollection.add(ad);

			mGroupCollection.add(lvl);


		}
	  
	  private void initSettingsPage() {
		  
		  // za listu
			mExpandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
			adapter = new ExpandableListAdapter(getActivity(),
					mExpandableListView, mGroupCollection);

			mExpandableListView.setAdapter(adapter);
			
			mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
				
				@Override
				public void onGroupExpand(int groupPosition) {
					
					if(lastExpandedGroupIndx < 5 && lastExpandedGroupIndx  != groupPosition){
						
						mExpandableListView.collapseGroup(lastExpandedGroupIndx);
						
					}
					
					lastExpandedGroupIndx = groupPosition;
				}
			});
//	mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//				
//				@Override
//				public boolean onChildClick(ExpandableListView parent, View v,
//						int groupPosition, int childPosition, long id) {
//					
//					//
//					
//					((MainActivity) getActivity()).fetchQuestions(adapter.language, adapter.level);
//					return true;
//				}
//			});
//	
	  }
	  


}
