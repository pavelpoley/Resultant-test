package com.pavelpoley.resultant_test.ui.stocklist;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.pavelpoley.resultant_test.common.SingleLiveEvent;
import com.pavelpoley.resultant_test.model.Stock;
import com.pavelpoley.resultant_test.network.StockResult;
import com.pavelpoley.resultant_test.network.StocksApi;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * ViewModel for StockListActivity
 */
public class StockListViewModel extends ViewModel {

    private static final String TAG = "StockListViewModel";

    public MutableLiveData<List<Stock>> stocksLiveData = new MutableLiveData<>();
    public SingleLiveEvent<String> errorLiveDataEvent = new SingleLiveEvent<>();

    private StocksApi stocksApi;

    private CompositeDisposable bag = new CompositeDisposable();

    public StockListViewModel(StocksApi stocksApi) {
        this.stocksApi = stocksApi;
    }


    //Get stocks list, notifies the activity when data received or when there is an error
    public void getStocksList() {

        bag.add(stocksApi.getStocks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, error -> onError(error.getMessage())));


    }

    private void handleResult(Response<StockResult> result) {
        if (result.isSuccessful()) {

            if (result.body() != null)
                stocksLiveData.setValue(result.body().getStock());

        } else {
            onError("Error accrued");
        }
    }

    private void onError(String msg) {
        errorLiveDataEvent.setValue(msg);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        bag.clear();
    }
}
