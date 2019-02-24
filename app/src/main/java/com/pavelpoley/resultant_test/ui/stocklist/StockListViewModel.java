package com.pavelpoley.resultant_test.ui.stocklist;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.pavelpoley.resultant_test.common.SingleLiveEvent;
import com.pavelpoley.resultant_test.model.Stock;
import com.pavelpoley.resultant_test.network.StockResult;
import com.pavelpoley.resultant_test.network.StocksRepository;

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

    private StocksRepository stocksRepository;

    private CompositeDisposable bag = new CompositeDisposable();


    public class State{
       boolean isLoading = false;
       List<Stock> stockList = null;
    }

    public StockListViewModel(StocksRepository stocksApi) {
        this.stocksRepository = stocksApi;
    }


    //Get stocks list, notifies the activity when data received or when there is an error
    public void getStocksList() {

        bag.add(stocksRepository.getStockList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, error -> onError(error.getMessage())));

    }

    private void handleResult(Response<StockResult> result) {
        if (result.isSuccessful()) {

            StockResult body = result.body();

            if (body != null)
                stocksLiveData.setValue(body.getStock());

        } else {
            onError("Connection error");
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
