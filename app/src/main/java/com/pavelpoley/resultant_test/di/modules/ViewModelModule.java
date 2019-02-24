package com.pavelpoley.resultant_test.di.modules;

import com.pavelpoley.resultant_test.network.StocksRepository;
import com.pavelpoley.resultant_test.ui.stocklist.StockListViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {

    @Provides
    StockListViewModelFactory getStockListViewModelFactory(StocksRepository stocksApi){
       return new StockListViewModelFactory(stocksApi);
    }
}
