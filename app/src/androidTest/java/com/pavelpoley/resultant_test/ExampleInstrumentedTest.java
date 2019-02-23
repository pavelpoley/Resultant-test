package com.pavelpoley.resultant_test;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.test.runner.AndroidJUnit4;

import com.pavelpoley.resultant_test.model.Stock;
import com.pavelpoley.resultant_test.network.StockResult;
import com.pavelpoley.resultant_test.network.StocksApi;
import com.pavelpoley.resultant_test.ui.stocklist.StockListViewModel;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import retrofit2.Response;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {


    StockListViewModel sut ;
    StocksApi stocksApi;
    Response<StockResult> response;
    private ArrayList<Stock> stockArrayList;

    @Before
    public void setup() {
        stocksApi = Mockito.mock(StocksApi.class);
        sut = new StockListViewModel(stocksApi);
        initResponse();
    }

    private void initResponse() {
        StockResult stockResult = new StockResult();
        initDummyStockList();
        stockResult.setStock(stockArrayList);
        response = Response.success(stockResult);
    }

    private void initDummyStockList() {
        stockArrayList = new ArrayList<>();
        stockArrayList.add(new Stock());
        stockArrayList.add(new Stock());
    }


    @Test
    public void getStockListReturnErrorNotifyObserver() {

        Mockito.when(stocksApi.getStocks()).thenReturn(new Single<Response<StockResult>>() {
            @Override
            protected void subscribeActual(SingleObserver<? super Response<StockResult>> observer) {
                    observer.onError(new Throwable("test error"));
            }
        });

        sut.errorLiveDataEvent.observeForever(new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                MatcherAssert.assertThat(s,CoreMatchers.is("test error"));
            }
        });


        sut.getStocksList();
    }

    @Test
    public void getStockListReturnSuccessNotifyObserver() {

        Mockito.when(stocksApi.getStocks()).thenReturn(new Single<Response<StockResult>>() {
            @Override
            protected void subscribeActual(SingleObserver<? super Response<StockResult>> observer) {
                observer.onSuccess(response);
            }
        });

        sut.stocksLiveData.observeForever(new Observer<List<Stock>>() {
            @Override
            public void onChanged(@Nullable List<Stock> stocks) {
                MatcherAssert.assertThat(stocks.size(),CoreMatchers.is(stockArrayList.size()));
            }
        });


        sut.getStocksList();
    }
}
