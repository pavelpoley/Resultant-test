package com.pavelpoley.resultant_test.ui.stocklist;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.pavelpoley.resultant_test.common.SingleLiveEvent;
import com.pavelpoley.resultant_test.model.Stock;
import com.pavelpoley.resultant_test.network.StockResult;
import com.pavelpoley.resultant_test.network.StocksApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * ViewModel for StockListActivity
 * */
public class StockListViewModel extends ViewModel {

    private static final String TAG = "StockListViewModel";

    MutableLiveData<List<Stock>> stocksLiveData = new MutableLiveData<>();
    SingleLiveEvent<String> errorEvent = new SingleLiveEvent<>();

    private StocksApi stocksApi;


    public StockListViewModel(StocksApi stocksApi) {
        this.stocksApi = stocksApi;
    }


    //Get stocks list, notifies the activity when data received or when there is an error
    void getStocksList() {

        stocksApi.getStocks().enqueue(new Callback<StockResult>() {

            @Override
            public void onResponse(@NonNull Call<StockResult> call, @NonNull Response<StockResult> response) {

                if (response.isSuccessful()) {

                    if (response.body() != null) {
                        //notify activity with new values
                        stocksLiveData.setValue(response.body().getStock());
                    }

                } else {
                    showErrorMessage();
                }
            }

            @Override
            public void onFailure(@NonNull Call<StockResult> call, @NonNull Throwable t) {
                showErrorMessage();
            }
        });

    }

    private void showErrorMessage() {
        errorEvent.setValue("");
    }

}
