package nepal.dina.babylon;

import android.database.SQLException;
import android.database.sqlite.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;

import nepal.dina.babylon.data.DataBaseHelper;
import nepal.dina.babylon.data.Question;
import nepal.dina.babylon.data.QuestionGenerator;
import nepal.dina.babylon.data.SmallQuestion;
import nepal.dina.babylon.expandableList.ExpandableListAdapter;
import nepal.dina.babylon.expandableList.GroupEntity;
import nepal.dina.babylon.expandableList.GroupEntity.GroupItemEntity;
import nepal.dina.babylon.listeners.MainTabLsn;
import nepal.dina.babylon.listeners.PlayTabLsn;
import nepal.dina.babylon.nexts.CollectionPagerAdapter;
import nepal.dina.babylon.nexts.ObjectFragment;
import nepal.dina.babylon.play.PlayFragment;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.style.SuperscriptSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.ImageButton;

import nepal.dina.babylon.listeners.CustomOnItemSelectedListener;

public class MainActivity extends SherlockFragmentActivity {

	public static int num;

	ArrayList<SmallQuestion> ret;

	public int numAll = 0;
	public int numRight = 0;
	public int numFetch = 200;

	private DataBaseHelper myDbHelper;

	public ArrayList<Question> questions;
	MainFragment mainFragment;

	Tab main;
	Tab play;
	Tab stats;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		questions = QuestionGenerator.getQuestions();

		setContentView(R.layout.main);
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); // nepotrebno

		// getSupportActionBar().setHomeButtonEnabled(true);

		getSupportActionBar().show();
		// prepareSettingsResource();
		// initSettingsPage();

		// Create new fragment and transaction
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		mainFragment = new MainFragment();
		fragmentTransaction.add(R.id.fragmentViewGroup, mainFragment);
		fragmentTransaction.commit();

		// action bar
		com.actionbarsherlock.app.ActionBar ab = getSupportActionBar();
		main = ab.newTab().setText("main");
		main.setTabListener(new MainTabLsn<MainFragment>(this, "tag",
				MainFragment.class));
		ab.addTab(main);
		ab.selectTab(main);

		play = ab.newTab().setText("play");
		play.setTabListener(new PlayTabLsn<PlayFragment>(this, "tag",
				PlayFragment.class));
		ab.addTab(play);

		stats = ab.newTab().setText("statistics");
		stats.setTabListener(new MainTabLsn<StatsFragment>(this, "tag",
				StatsFragment.class));
		ab.addTab(stats);

		try {
			myDbHelper = new DataBaseHelper(this);
			myDbHelper.createDataBase();
			fetchQuestions();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
//	@Override
//	public void onStart() {
//		// TODO Auto-generated method stub
//		super.onStart();
//		  
//		Spinner lng = (Spinner) findViewById(R.id.lng);
//		lng.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		addListenerOnSpinnerItemSelection();
//	}
//
//
//	private void addListenerOnSpinnerItemSelection() {
//		
//		
////		Spinner spinner = (Spinner) findViewById(R.id.lng);
////		spinner.setSelection(1);
//		lng.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//
//	}

	public void btnLngOnClck(View view) {

	}

	public void btnPlayOnClck(View view) {

		getSupportActionBar().selectTab(play);

	}

	public void onClckShow(View view) {

		String s = ret.get(numAll).getA();
		((Button) findViewById(R.id.btnShow)).setText(s);
		numAll++;

		((Button) findViewById(R.id.btnShow)).setClickable(false);

		((ImageButton) findViewById(R.id.btnKrivo)).setVisibility(View.VISIBLE);
		((Button) findViewById(R.id.btnTocno)).setVisibility(View.VISIBLE);
	}

	private void enableShow() {
		((Button) findViewById(R.id.btnShow)).setText("Show ? ");
		((Button) findViewById(R.id.btnShow)).setClickable(true);
	}

	public void instantiateQuestion() {

		String s = ret.get(numAll).getQ();
		((TextView) findViewById(R.id.question)).setText(s);

		((ImageButton) findViewById(R.id.btnKrivo)).setVisibility(View.GONE);
		((Button) findViewById(R.id.btnTocno)).setVisibility(View.GONE);

		enableShow();
	}

	public void onClckKrivo(View view) {

		instantiateQuestion();
	}

	public void onClckTocno(View view) {

		numRight++;
		instantiateQuestion();
	}

	private void fetchQuestions() {

		myDbHelper.openDataBase();
		
		String language = "ger";
		if(mainFragment.language < 100){
			language = Mapper.getLanguage(mainFragment.language);
		}
		
		String level = "A";
		if(mainFragment.level < 100){
			level = Mapper.getLanguage(mainFragment.level);
		}
		ret = myDbHelper.getQuestions(language, level, "100");
		myDbHelper.closeDataBase();
	}
	
	
	enum Mapper{
		GERMAN, FRENCH, ITALIAN;
		
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

}
