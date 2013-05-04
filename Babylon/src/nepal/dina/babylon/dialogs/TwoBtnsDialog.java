package nepal.dina.babylon.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class TwoBtnsDialog extends DialogFragment{

	Context mContext;
    public TwoBtnsDialog() {
        mContext = getActivity();
    }
    
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("Hey");
        alertDialogBuilder.setMessage("Do u want to continue old game?");
      alertDialogBuilder.setPositiveButton("Yes",  new DialogInterface.OnClickListener() {

          @Override
          public void onClick(DialogInterface dialog, int which) {
        	  // TODO napravi nes da pitanja budu nova, reset brojac
              dialog.dismiss();
          }
      });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            	// TODO stara pitanja, isti brojac
                dialog.dismiss();
            }
        });

        return alertDialogBuilder.create();
    }
    
   
}
