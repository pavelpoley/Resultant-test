package com.pavelpoley.resultant_test;

import android.app.Application;

import com.pavelpoley.resultant_test.di.ApplicationComponent;
import com.pavelpoley.resultant_test.di.DaggerApplicationComponent;
import com.pavelpoley.resultant_test.di.modules.RetrofitModule;

public class App extends Application {

    private ApplicationComponent applicationComponent;

    //test1

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .retrofitModule(new RetrofitModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }
}
