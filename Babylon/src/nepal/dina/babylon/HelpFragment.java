package nepal.dina.babylon;

import nepal.dina.babylon.dialogs.TwoBtnsDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HelpFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		

		View view = inflater.inflate(R.layout.help_fragment, container, false);
		

		return view;
	}
}
