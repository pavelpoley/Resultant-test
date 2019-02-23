package com.pavelpoley.resultant_test.ui.stocklist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pavelpoley.resultant_test.R;
import com.pavelpoley.resultant_test.di.modules.ViewModelModule;
import com.pavelpoley.resultant_test.model.Stock;
import com.pavelpoley.resultant_test.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;


public class StockListActivity extends BaseActivity {

    private static final String TAG = "StockListActivity";

    private final static int REFRESH_INTERVAL = 3000;

    private StockListAdapter adapter;

    //progress bar visible on start while data loading and when user refresh data manually
    private ProgressBar progressBar;

    //Handler used to execute tasks in interval, executes between onStart and onStop
    private Handler handler = new Handler();

    private StockListViewModel stockListViewModel;

    //Observers for LiveData
    private Observer<List<Stock>> dataObserver = getDataObserver();
    private Observer<String> errorObserver = getErrorObserver();

    @Inject StockListViewModelFactory stockListViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_list);

        initProgressBar();
        initRecycleView();


        //inject component
        getApplicationComponent().getStockListActivityComponent(new ViewModelModule()).inject(this);



        //init viewModel
        stockListViewModel = ViewModelProviders
                .of(this, stockListViewModelFactory)
                .get(StockListViewModel.class);


        //observes for data, triggers when server returns response (stock list) successfully
        stockListViewModel.stocksLiveData.observe(this, dataObserver);

        //observes for connection error
        stockListViewModel.errorLiveDataEvent.observe(this, errorObserver);
    }


    private void initProgressBar() {
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }


    //Creates observer to handle connection error, shows toast message
    private Observer<String> getErrorObserver() {
        return errorObserver = s -> Toast.makeText(StockListActivity.this, getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
    }


    //Creates observer to handle data from http request
    private Observer<List<Stock>> getDataObserver() {
        return stocks -> {
            handleResponse(stocks);
            progressBar.setVisibility(View.INVISIBLE);
        };
    }

    //init recycleView with adapter
    private void initRecycleView() {
        RecyclerView rvStocks = findViewById(R.id.rv_stocks);
        rvStocks.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StockListAdapter();
        rvStocks.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        handler.postDelayed(getRefreshRunnable(), 0);
    }

    @Override
    protected void onStop() {
        super.onStop();

        //stop callbacks
        handler.removeCallbacksAndMessages(null);
    }

    @NonNull
    private Runnable getRefreshRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                stockListViewModel.getStocksList();
                handler.postDelayed(this, REFRESH_INTERVAL);
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate option menu
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle option menu click

        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            refreshList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Manually refresh list
    private void refreshList() {
        stockListViewModel.getStocksList();
        progressBar.setVisibility(View.VISIBLE);
    }

    //Init adapter when new data received
    private void handleResponse(List<Stock> stocks) {
        adapter.setList(stocks);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        //remove all observers
        stockListViewModel.stocksLiveData.removeObservers(this);
    }
}
