package com.sasmobile.initialsetup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.sasmobile.MainActivity;
import com.sasmobile.R;
import com.sasmobile.SASBaseActivity;
import com.sasmobile.utils.SasConstants;

public class SplashActivity extends SASBaseActivity {

    public static final int SPLASH_DELAY_TIME_MILLIS = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences myPrefs = getSharedPreferences("sasPrefs", MODE_PRIVATE);
                boolean isTandCAccepted = myPrefs.getBoolean(SasConstants.IS_T_AND_C_ACCEPTED, false);
                boolean isTutorialVisited = myPrefs.getBoolean(SasConstants.IS_TUTORIAL_VISITED, false);
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                if (isTandCAccepted && isTutorialVisited) {
//                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                } else if (isTandCAccepted && SasConstants.IS_TUTORIAL_REQUIRED) {
//                    startActivity(new Intent(SplashActivity.this, TutorialActivity.class));
//                } else {
//                    startActivity(new Intent(SplashActivity.this, TermsAndConditions.class));
//                }

                finish();
            }
        }, SPLASH_DELAY_TIME_MILLIS);
    }
}
