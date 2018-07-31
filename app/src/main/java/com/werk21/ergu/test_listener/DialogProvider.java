package com.werk21.ergu.test_listener;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment; // if android.support.app.DialogFragment is used the call is: .show(this.getFragmentManager(),"id");
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;


/**
 * This class assembles a very basic interactive and dynamic Dialog.
 * It assigns a title, a message text and texts for the positive and the
 * negative button. Further information on how to extend this basic example
 * can be found here: https://developer.android.com/guide/topics/ui/dialogs
 */
public class DialogProvider extends DialogFragment {
    /**
     * These four string variables will be initialized as soon as this
     * class is coming up to the screen which is at the .show()-call.
     */
    String title, txt, posBtn, negBtn;


    /**
     * Initializes the four class fields and builds the dialog containing a title,
     * a message part and tow buttons, one positive and a negative one.
     * @param savedInstanceState Androids standard buffer
     * @return a Dialog object on which to call the .show()-method to start the dialog.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //++++++++++++ initializes class fields from bundle +++++++++++++++++
        title = this.getArguments().getString("title");
        txt = this.getArguments().getString("message");
        posBtn = this.getArguments().getString("pos_btn");
        negBtn = this.getArguments().getString("neg_btn");

        //++++++++++++ initializes the Dialog +++++++++++++++++++++++++++++++

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder                     // Shorthand for builder.method1().method2().method3();
                .setMessage(txt)
                .setTitle(title)
                .setPositiveButton(posBtn, new DialogInterface.OnClickListener() { //Anonymous constructor call to a class of the type DialogInterface.OnClickListener
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "SMART", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(negBtn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "DUMB", Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();  // this returns the assembled object ready to have the show()-method called on it.
    } // end of onCreate();
} // end of class
