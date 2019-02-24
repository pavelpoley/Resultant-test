package com.pavelpoley.resultant_test.network;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class StocksRepository {

    private StocksApi stocksApi;

    @Inject
    public StocksRepository(StocksApi stocksApi) {
        this.stocksApi = stocksApi;
    }


    public Single<Response<StockResult>> getStockList() {
        return stocksApi.getStocks();
    }
}
