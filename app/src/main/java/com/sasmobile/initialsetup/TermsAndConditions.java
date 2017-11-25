package com.sasmobile.initialsetup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;

import com.sasmobile.MainActivity;
import com.sasmobile.R;
import com.sasmobile.SASBaseActivity;
import com.sasmobile.tutorial.TutorialActivity;
import com.sasmobile.utils.SasConstants;

public class TermsAndConditions extends SASBaseActivity {
    private CheckBox mTAndCChk;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.loadUrl("file:///android_asset/terms_conditions.html");
        mTAndCChk = (CheckBox) findViewById(R.id.checkbox);

    }

    public void onTAndCAccepted(View view) {

        if (!mTAndCChk.isChecked()) {
            return;
        }

        if (SasConstants.IS_TUTORIAL_REQUIRED) {
            SharedPreferences myPrefs = this.getSharedPreferences("sasPrefs", MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = myPrefs.edit();
            prefsEditor.putBoolean(SasConstants.IS_T_AND_C_ACCEPTED, true);
            prefsEditor.commit();
            startActivity(new Intent(TermsAndConditions.this, TutorialActivity.class));
        } else {
            startActivity(new Intent(TermsAndConditions.this, MainActivity.class));
        }
        finish();

    }
}
