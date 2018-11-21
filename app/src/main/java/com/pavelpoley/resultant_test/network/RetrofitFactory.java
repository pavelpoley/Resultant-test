package com.pavelpoley.resultant_test.network;

import com.pavelpoley.resultant_test.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static StocksApi stocksApi;

    //Get api instance to create http requests
    public static StocksApi get() {
        if (stocksApi == null)
            stocksApi = getRetrofit().create(StocksApi.class);

        return stocksApi;
    }

    //Get retrofit client
    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
