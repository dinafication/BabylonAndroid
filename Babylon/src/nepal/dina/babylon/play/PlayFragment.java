package nepal.dina.babylon.play;

import java.util.ArrayList;
import java.util.List;

import nepal.dina.babylon.MainActivity;
import nepal.dina.babylon.R;
import nepal.dina.babylon.data.DataBaseHelper;
import nepal.dina.babylon.data.Question;
import nepal.dina.babylon.data.QuestionGenerator;
import nepal.dina.babylon.data.SmallQuestion;
import nepal.dina.babylon.expandableList.ExpandableListAdapter;
import nepal.dina.babylon.expandableList.GroupEntity;
import nepal.dina.babylon.expandableList.GroupEntity.GroupItemEntity;
import nepal.dina.babylon.nexts.ObjectFragment;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Button;
import android.widget.ImageButton;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class PlayFragment extends Fragment {


	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		

		View view = inflater.inflate(R.layout.memo_question, container, false);
		

		return view;
	}
	
//	@Override
//	public void onAttach(Activity activity) {
//		// TODO Auto-generated method stub
//		super.onAttach(activity);
//		
//		((MainActivity)getActivity()).instantiateQuestion();
//	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		((MainActivity)getActivity()).instantiateQuestion();
	}
	
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		((MainActivity)getActivity()).instantiateQuestion();
//	}
}