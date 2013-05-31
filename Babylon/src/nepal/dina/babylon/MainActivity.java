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
import nepal.dina.babylon.data.MyGroup;
import nepal.dina.babylon.data.Question;
import nepal.dina.babylon.data.QuestionGenerator;
import nepal.dina.babylon.data.SmallQuestion;
import nepal.dina.babylon.dialogs.OneBtnDialog;
import nepal.dina.babylon.expandableList.ExpandableListAdapter;
import nepal.dina.babylon.expandableList.GroupEntity;
import nepal.dina.babylon.expandableList.GroupEntity.GroupItemEntity;
import nepal.dina.babylon.listeners.MainTabLsn;
import nepal.dina.babylon.listeners.PlainTabLsn;
import nepal.dina.babylon.nexts.CollectionPagerAdapter;
import nepal.dina.babylon.nexts.ObjectFragment;
import nepal.dina.babylon.play.PlayFragment;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.style.SuperscriptSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
import nepal.dina.babylon.my.MyFragment;

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
	Tab my;
	Tab stats;
	Tab help;

	public String language;
	public String level;

	private MainTabLsn<MainFragment> mainTabLsn;

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
		main = ab.newTab().setText("Home");
		mainTabLsn = new MainTabLsn<MainFragment>(this, "tag",
				MainFragment.class);
		main.setTabListener(mainTabLsn);
		ab.addTab(main);
		ab.selectTab(main);

		my = ab.newTab().setText("MY");
		my.setTabListener(new PlainTabLsn<MyFragment>(this, "tag",
				MyFragment.class));
		ab.addTab(my);

		stats = ab.newTab().setText("%");
		stats.setTabListener(new PlainTabLsn<StatsFragment>(this, "tag",
				StatsFragment.class));
		ab.addTab(stats);

		help = ab.newTab().setText("HELP");
		help.setTabListener(new PlainTabLsn<HelpFragment>(this, "tag",
				HelpFragment.class));
		// TODO rename in myFragment
		ab.addTab(help);
		
		// help.se

		try {
			myDbHelper = new DataBaseHelper(this);
			myDbHelper.createDataBase();
			// fetchQuestions();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// @Override
	// public void onStart() {
	// // TODO Auto-generated method stub
	// super.onStart();
	//
	// Spinner lng = (Spinner) findViewById(R.id.lng);
	// lng.setOnItemSelectedListener(new OnItemSelectedListener() {
	//
	// @Override
	// public void onItemSelected(AdapterView<?> arg0, View arg1,
	// int arg2, long arg3) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void onNothingSelected(AdapterView<?> arg0) {
	// // TODO Auto-generated method stub
	//
	// }
	// });
	// addListenerOnSpinnerItemSelection();
	// }
	//
	//
	// private void addListenerOnSpinnerItemSelection() {
	//
	//
	// // Spinner spinner = (Spinner) findViewById(R.id.lng);
	// // spinner.setSelection(1);
	// lng.setOnItemClickListener(new OnItemClickListener() {
	//
	// @Override
	// public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
	// long arg3) {
	// // TODO Auto-generated method stub
	//
	// }
	// });
	//
	// }

	@Override
	public void onBackPressed() {
		// ako si unutar playa onda samo treba home
		
		if(mainTabLsn.getSelectedfragment().getClass().equals(PlayFragment.class)){
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			mainTabLsn.onHome(main, ft);
			ft.commit();
		}
		else{
			super.onBackPressed();
		}
		
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}

	public void btnPlayOnClck(View view) {

		if (language == null || level == null) {
			// TODO
			FragmentManager fm = getSupportFragmentManager();
			OneBtnDialog testDialog = new OneBtnDialog();
			testDialog.show(fm, "not_selected");

		} else {
			fetchQuestions();

			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			mainTabLsn.onPlay(main, ft);
			ft.commit();
		}

	}

	public void onClckShow(View view) {

		String s = ret.get(numAll).getA();
		((Button) findViewById(R.id.btnShow)).setText(s);
		numAll++;

		((Button) findViewById(R.id.btnShow)).setClickable(false);

		((ImageButton) findViewById(R.id.btnKrivo)).setVisibility(View.VISIBLE);
		((ImageButton) findViewById(R.id.btnTocno)).setVisibility(View.VISIBLE);
	}

	private void enableShow() {
		((Button) findViewById(R.id.btnShow)).setText("Show ? ");
		((Button) findViewById(R.id.btnShow)).setClickable(true);
	}

	public void instantiateQuestion() {

		// fetchQuestions();
		String s = ret.get(numAll).getQ();
		((TextView) findViewById(R.id.question)).setText(s);

		((ImageButton) findViewById(R.id.btnKrivo)).setVisibility(View.GONE);
		((ImageButton) findViewById(R.id.btnTocno)).setVisibility(View.GONE);

		enableShow();
	}

	public void onClckKrivo(View view) {

		instantiateQuestion();
	}

	public void onClckTocno(View view) {

		numRight++;
		instantiateQuestion();
	}

	public void fetchQuestions() {

		myDbHelper.openDataBase();

		ret = myDbHelper.getQuestions(language, level, "100");

		myDbHelper.closeDataBase();
	}


	// iz baze izvuce Moje rijeci
	public ArrayList<MyGroup> getMyWords(){
		
		myDbHelper.openDataBase();

		ArrayList<MyGroup> groups  = myDbHelper.getMyGroups();

		myDbHelper.closeDataBase();
		return groups;
	}
}
