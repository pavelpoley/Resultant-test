
package com.pavelpoley.resultant_test.network;

import com.pavelpoley.resultant_test.model.Stock;

import java.util.List;

/**
 * This class represent json result from server as POJO object
 * */
public class StockResult {

    private List<Stock> stock = null;
    private String asOf;

    public List<Stock> getStock() {
        return stock;
    }

    public void setStock(List<Stock> stock) {
        this.stock = stock;
    }

    public String getAsOf() {
        return asOf;
    }

    public void setAsOf(String asOf) {
        this.asOf = asOf;
    }

}
