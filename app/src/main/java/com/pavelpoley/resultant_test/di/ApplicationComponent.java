package com.pavelpoley.resultant_test.di;

import com.pavelpoley.resultant_test.di.modules.RetrofitModule;
import com.pavelpoley.resultant_test.di.modules.ViewModelModule;
import com.pavelpoley.resultant_test.network.StocksApi;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetrofitModule.class})
public interface ApplicationComponent {

    StocksApi getStocksApi();

    StockListActivityComponent getStockListActivityComponent(ViewModelModule viewModelModule);
}
