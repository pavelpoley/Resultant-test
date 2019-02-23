package com.pavelpoley.resultant_test.ui.base;

import android.support.v7.app.AppCompatActivity;

import com.pavelpoley.resultant_test.App;
import com.pavelpoley.resultant_test.di.ApplicationComponent;

public abstract class BaseActivity extends AppCompatActivity {

    public ApplicationComponent getApplicationComponent(){
        return ((App)getApplication()).getApplicationComponent();
    }
}
