package nepal.dina.babylon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import nepal.dina.babylon.data.DataBaseHelper;
import nepal.dina.babylon.expandableList.ExpandableListAdapter;
import nepal.dina.babylon.expandableList.GroupEntity;
import nepal.dina.babylon.expandableList.GroupEntity.GroupItemEntity;
import nepal.dina.babylon.nexts.ObjectFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ExpandableListView;
import android.widget.TextView;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MainFragment extends Fragment {

	public ExpandableListView mExpandableListView;
	public ExpandableListAdapter adapter;
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
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_main, container, false);
		getActivity().setContentView(R.layout.main);
		
		initSettingsPage();

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

	}

	private void prepareSettingsResource() {

		mGroupCollection = new ArrayList<GroupEntity>();


		// jezik za ucenje
		GroupEntity lng = new GroupEntity();
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
		mExpandableListView = (ExpandableListView) view
				.findViewById(R.id.expandableListView);
		// ako se layout prv put prikazuje, treba stvoriti novi adapter, inace ne
		if(adapter == null){
			adapter = new ExpandableListAdapter(getActivity(), mExpandableListView,
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
