package com.pavelpoley.resultant_test.di;

import com.pavelpoley.resultant_test.di.modules.ViewModelModule;
import com.pavelpoley.resultant_test.ui.stocklist.StockListActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {ViewModelModule.class})
public interface StockListActivityComponent {
    void inject(StockListActivity activity);
}
