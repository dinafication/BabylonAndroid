package nepal.dina.babylon.nexts;

import com.actionbarsherlock.app.ActionBar;

import nepal.dina.babylon.MainActivity;
import nepal.dina.babylon.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ObjectFragment extends Fragment {

	public static final String ARG_OBJECT = "object";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView;
		Bundle args = getArguments();
		int num = args.getInt(ARG_OBJECT);
		
		// TODO ucitaj iz propertisa
		if (num == 4) {
			rootView = inflater.inflate(R.layout.last_question, container,
					false);
		} else {
			rootView = inflater.inflate(R.layout.question, container, false);
		}

		((TextView) rootView.findViewById(R.id.questionTxt))
				.setText(((MainActivity) getActivity()).questions.get(num - 1)
						.getQ());

		((TextView) rootView.findViewById(R.id.radio_a1))
				.setText(((MainActivity) getActivity()).questions.get(num - 1)
						.getA1());

		((TextView) rootView.findViewById(R.id.radio_a2))
				.setText(((MainActivity) getActivity()).questions.get(num - 1)
						.getA2());

		((TextView) rootView.findViewById(R.id.radio_a3))
				.setText(((MainActivity) getActivity()).questions.get(num - 1)
						.getA3());
		return rootView;
	}
}
