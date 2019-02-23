package com.pavelpoley.resultant_test.network;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;

public interface StocksApi {

    /**
     * GET request, requests for list of stocks
     */
    @GET("stocks.json")
    public Single<Response<StockResult>> getStocks();
}
