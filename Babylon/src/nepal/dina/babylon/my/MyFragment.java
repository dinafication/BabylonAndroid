package nepal.dina.babylon.my;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import nepal.dina.babylon.MainActivity;
import nepal.dina.babylon.Mapper;
import nepal.dina.babylon.R;
import nepal.dina.babylon.data.MyGroup;
import nepal.dina.babylon.data.Pair;
import nepal.dina.babylon.dialogs.TwoBtnsDialog;
import nepal.dina.babylon.expandableList.ExpandableAdapterMy;
import nepal.dina.babylon.expandableList.ExpandableListAdapter;
import nepal.dina.babylon.expandableList.GroupEntity;
import nepal.dina.babylon.expandableList.GroupEntity.GroupItemEntity;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class MyFragment extends Fragment{
	
	public ExpandableListView mExpandableListView;
	public ExpandableAdapterMy adapter;
	private List<GroupEntity> mGroupCollection;

	View view;

	private int lastExpandedGroupIndx = 100;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
		prepareSettingsResource();		
	}

	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		onAttach(getActivity());

		view = inflater.inflate(R.layout.fragment_my, container, false);
		getActivity().setContentView(R.layout.main);
		
		initSettingsPage();

		return view;
	}


	private void prepareSettingsResource() {

		mGroupCollection = new ArrayList<GroupEntity>();
		
		HashSet<MyGroup> s = ((MainActivity)getActivity()).getMyWords();


		GroupEntity ge = null;
		GroupItemEntity gie = null;
		
		Iterator<MyGroup> itr = s.iterator();
		while(itr.hasNext()){
			
			MyGroup mg = itr.next();
			
			ge = new GroupEntity();
			ge.Name = mg.getName();
			
			ArrayList<Pair<String, String>> ws = mg.getWords();
			for(int j = 0; j < ws.size(); j++){
				
				gie  = ge.new GroupItemEntity();
				gie.Name = ws.get(j).first;
				gie.note = ws.get(j).second;
				
				ge.GroupItemCollection.add(gie);
			}
			mGroupCollection.add(ge);
		}

	}

	private void initSettingsPage() {

		// za listu
		mExpandableListView = (ExpandableListView) view
				.findViewById(R.id.expandableListView);
		// ako se layout prv put prikazuje, treba stvoriti novi adapter, inace ne
		if(adapter == null){
			adapter = new ExpandableAdapterMy(getActivity(), mExpandableListView,
					mGroupCollection);
		}
		else{
			adapter.setmExpandableListView(mExpandableListView);
		}
		

		mExpandableListView.setAdapter(adapter);

		mExpandableListView
				.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

					@Override
					public void onGroupExpand(int groupPosition) {

						if (lastExpandedGroupIndx < 5
								&& lastExpandedGroupIndx != groupPosition) {

							mExpandableListView
									.collapseGroup(lastExpandedGroupIndx);
							adapter.groupStatus[groupPosition] = 0;
						}
						adapter.groupStatus[groupPosition] = 1;

						lastExpandedGroupIndx = groupPosition;
					}
				});

		// U biti se ne koristi, jer je view prosiren preko cijele sirine,
		// ostavljen za svaki slucaj
		mExpandableListView
				.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

					@Override
					public boolean onChildClick(ExpandableListView parent,
							View v, int groupPosition, int childPosition,
							long id) {

						if (groupPosition == 0) {
							((MainActivity) getActivity()).language = Mapper
									.getLanguage(childPosition);
						}

						if (groupPosition == 1) {
							((MainActivity) getActivity()).level = Mapper
									.getLevel(childPosition);
						}

						mExpandableListView.collapseGroup(groupPosition);
						return true;
					}
				});

	}
}
