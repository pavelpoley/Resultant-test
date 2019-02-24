package com.pavelpoley.resultant_test.di;

import com.pavelpoley.resultant_test.di.modules.RetrofitModule;
import com.pavelpoley.resultant_test.di.modules.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetrofitModule.class})
public interface ApplicationComponent {

    StockListActivityComponent getStockListActivityComponent(ViewModelModule viewModelModule);
}
