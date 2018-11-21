package com.pavelpoley.resultant_test.ui.stocklist;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.pavelpoley.resultant_test.network.StocksApi;

/**
 * Factory for StockListViewModel
 */
public class StockListViewModelFactory implements ViewModelProvider.Factory {

    private StocksApi stocksApi;

    public StockListViewModelFactory(StocksApi stocksApi) {
        this.stocksApi = stocksApi;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new StockListViewModel(stocksApi);
    }
}
