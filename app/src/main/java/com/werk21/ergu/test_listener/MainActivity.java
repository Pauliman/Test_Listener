package com.werk21.ergu.test_listener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This class manages a single button that triggers a dynamic dialog.
 * The button is assigned an onClick-listener programmatically in the start()-method while the
 * same effect could be achieved by setting up android:onClick="makeDialog" in the layout-XML-file.
 * The button may trigger two versions of a Dialog makeDialog() using a DialogProvider extending DialogFragment and 
 * the other, makeSecondDialog(), uses an AlertDialog the behaviour of which is applied after the Dialog was created.
 * This allows for more flexibility as the content of the AlertDialog object can easily be rendered by the methods in the AlertDialog's
 * onClick-Listener.
 * @author Pauliman
 */
public class MainActivity extends AppCompatActivity {
    /**
     * the only View element of this activity
     */
    private Button btn;    

    /**
     * Gets a handle on the layout-XML resource and the button within it.
     * @param savedInstanceState the Android standard buffer.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = this.findViewById(R.id.btn_press);        
        start();
    } // end of onCreate()

    /**
     * Assigns an onClick-Listener to the button and calls the makeDialog()-method
     * when the button is pressed. Yeah!
     */
    private void start(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //makeDialog();       uncomment to activate
                makeSecondDialog(); // comment to deactivate
            }
        });
    } // end of start()

    /**
     * Creates a DialogFragment-object and assigns a Bundle object to it.
     * In doing so it passes arguments to the Fragment. This is the default
     * way to transmit data to a fragment and adhere to the life cycle rules.
     * At last the show()-method is called on the Fragment-object passing a
     * FragmentManager-object and a identifier String that represents the internal
     * name of this dialog. It's important not to mix the required imports as android.app.* 
     * and android.supportv7.app.* cause a hard time for the compiler.
     */
    public void makeDialog(){
        DialogProvider dp = new DialogProvider();
        Bundle bundle = new Bundle();
        bundle.putString("title", "MY TITLE");
        bundle.putString("message", "THIS IS MY MESSAGE TO YOU!");
        bundle.putString("pos_btn", "GOT IT");
        bundle.putString("neg_btn", "WHAT?!");
        dp.setArguments(bundle);
        dp.show(this.getSupportFragmentManager(), "message");
    } // end of makeDialog()

    /**
     * Creates an AlertDialog and assigns the primary components title, message, positive and negative
     * buttons. However, the definition of the button's behaviour is taken outside of the dialog object
     * and thus allows interaction between the activity and the elements of the dialog.
     */
    private void makeSecondDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("The Ice Man")
                .setMessage("ICECREAM ANYONE?")
                .setPositiveButton("OK", null)
                .setNegativeButton("NO", null);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        Button posBtn = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        posBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv_message = (TextView) alertDialog.findViewById(android.R.id.message);
                tv_message.setText("Here you are Buddy, knock yourself out");

            }
        });
        Button negBtn = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        negBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView tv_message = (TextView) alertDialog.findViewById(android.R.id.message);
                //Even though this works like a charm for message, it did not work for me with 'title' 
                //which is also a standard Dialog component. Strange.                
                tv_message.setText("Good. More for me then.");
                //tv_mesage.setText(R.string.my_string_in_resource_folder);
                /*
                To make it more dynamic still, you can get an int[] where you store the values that you
                might need here. You best retrieve those values in an init()-mehtod of your Acrivity.
                int[] myStrings = int[]{
                this.getResources().getIdentifier("com.werk21.ergu.test_listener:string/my_string", null, null),
                this.getResources().getIdentifier("com.werk21.ergu.test_listener:string/my_second_string", null, null)};
                Then the following call would be possible where x is an int as your code needs it.
                tv_mesage.setText(myStrings[x]);
                */
            }
        });
    }
} // end of class
