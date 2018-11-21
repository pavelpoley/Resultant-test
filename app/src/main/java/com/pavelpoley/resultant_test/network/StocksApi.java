package com.pavelpoley.resultant_test.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StocksApi {

    /**
     * GET request, requests for list of stocks
     */
    @GET("stocks.json")
    Call<StockResult> getStocks();
}
