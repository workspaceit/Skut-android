package prochito.its.skut.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.TextView;

import prochito.its.skut.R;
import prochito.its.skut.Support.SharedPreferencesManager;

public class SplashScreenActivity extends AppCompatActivity implements Animation.AnimationListener {

    private static int SPLASH_TIME_OUT = 5000;
    private TextView mWelcomeMsg;
    private Animation animBlink;

    private SharedPreferencesManager mySharedPreferences;
    private boolean isMobileVerified = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

/*        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/


        mySharedPreferences = new SharedPreferencesManager(this);
        isMobileVerified = mySharedPreferences.getBooleanValueFromSharePref("isMobileVerified");

        //hide status bar

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start app main activity
                if (isMobileVerified) {
                    Intent i = new Intent(SplashScreenActivity.this, HomeActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(SplashScreenActivity.this, MobileVerificationActivity.class);
                    startActivity(i);
                }
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }


    @Override
    public void onAnimationEnd(Animation animation) {
        // Take any action after completing the animation

        // check for blink animation
        if (animation == animBlink) {
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }
}
