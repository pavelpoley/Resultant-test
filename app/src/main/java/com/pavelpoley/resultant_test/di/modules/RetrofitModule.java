package com.pavelpoley.resultant_test.di.modules;

import com.pavelpoley.resultant_test.Config;
import com.pavelpoley.resultant_test.network.StocksApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {


    //Get api instance to create http requests
    @Singleton
    @Provides
    StocksApi get(Retrofit retrofit) {
        return retrofit.create(StocksApi.class);
    }

    //Get retrofit client
    @Singleton
    @Provides
    Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
