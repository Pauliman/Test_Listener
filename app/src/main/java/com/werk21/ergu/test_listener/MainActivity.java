package com.werk21.ergu.test_listener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * This class manages a single button that triggers a dynamical dialog.
 * The button is assigned an onClick-listener programmatically in the start()-method while the
 * same effect could be achieved by setting up android:onClick="makeDialog" in the layout-XML-file. *
 */
public class MainActivity extends AppCompatActivity {
    /**
     * the only View element of this activity
     */
    Button btn;

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
                makeDialog();
            }
        });
    } // end of start()

    /**
     * Creates a DialogFragment-object and assigns a Bundle object to it.
     * In doing so it passes arguments to the Fragment. This is the default
     * way to transmit data to a fragment and adhere to the life cycle rules.
     * At last the show()-method is called on the Fragment-object passing a
     * FragmentManager-object and a identifier String that represents the internal
     * name of this dialog.
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
} // end of class
