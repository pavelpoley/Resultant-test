package com.pavelpoley.resultant_test.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.pavelpoley.resultant_test.R;
import com.pavelpoley.resultant_test.ui.stocklist.StockListActivity;

public class SplashActivity extends AppCompatActivity {

    private final static int SPLASH_DELAY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //splash screen delay
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, StockListActivity.class));
            finish();
        }, SPLASH_DELAY);
    }
}
