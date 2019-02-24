package com.pavelpoley.resultant_test.ui.stocklist;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.pavelpoley.resultant_test.network.StocksRepository;

/**
 * Factory for StockListViewModel
 */
public class StockListViewModelFactory implements ViewModelProvider.Factory {

    private StocksRepository stocksRepository;

    public StockListViewModelFactory(StocksRepository stocksApi) {
        this.stocksRepository = stocksApi;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new StockListViewModel(stocksRepository);
    }
}
