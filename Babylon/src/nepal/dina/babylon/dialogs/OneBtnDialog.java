package nepal.dina.babylon.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class OneBtnDialog extends DialogFragment{

	Context mContext;
    public OneBtnDialog() {
        mContext = getActivity();
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("Hey");
        alertDialogBuilder.setMessage("Could u please select language and level");
//      alertDialogBuilder.setPositiveButton("OK", null);
        alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return alertDialogBuilder.create();
    }
   
}
