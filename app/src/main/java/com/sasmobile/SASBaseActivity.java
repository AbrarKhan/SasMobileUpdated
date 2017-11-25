package com.sasmobile;

import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;

public class SASBaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sasbase);
    }

    /**
     * This method is overriden to support multi dex.
     * else in some devices application will crash.
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        /**
         * To this class add multi dex library in gradle,
         * and enable multi-dex in default config in gradle file
         */
        MultiDex.install(this);
    }
}
