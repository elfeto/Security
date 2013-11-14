package hello.tab.tabhello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
 
/**
 * Splash screen activity
 *
 * @author Catalin Prata
 */
public class SplashScreen extends Activity {
 
    // used to know if the back button was pressed in the splash screen activity and avoid opening the next activity
    private boolean mIsBackButtonPressed;
    private static final int SPLASH_DURATION = 1000; // 2 seconds
 
 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.splash_screen);
 
        Handler handler = new Handler();
 
        // run a thread after 2 seconds to start the home screen
        handler.postDelayed(new Runnable() {
 
            @Override
            public void run() {
                finish();
                
            }
        }, SPLASH_DURATION); // time in milliseconds (1 second = 1000 milliseconds) until the run() method will be called
        setContentView(R.layout.main);
    }
 
    @Override
   public void onBackPressed() {
 
        // set the flag to true so the next activity won't start up
        mIsBackButtonPressed = true;
        super.onBackPressed();
 
    }
}