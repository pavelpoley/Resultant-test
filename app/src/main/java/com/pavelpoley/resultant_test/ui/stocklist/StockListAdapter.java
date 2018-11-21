package com.pavelpoley.resultant_test.ui.stocklist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pavelpoley.resultant_test.common.FormatUtils;
import com.pavelpoley.resultant_test.R;
import com.pavelpoley.resultant_test.model.Stock;

import java.util.ArrayList;
import java.util.List;


/**
 * Adapter for list of stocks.
 * Contain 2 view types, the first is single decor view with names of columns, the
 * second represent stock.
 * */
public class StockListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "StockListAdapter";

    private List<Stock> stockList = new ArrayList<>();

    private static final int TYPE_STOCK = 7;
    private static final int TYPE_STOCK_DESCRIPTION = 8;

    //Set list of stocks
    void setList(List<Stock> list) {
        stockList.clear();
        addDescriptionBar();
        stockList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view;

        switch (viewType) {

            case TYPE_STOCK_DESCRIPTION:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_table_description, viewGroup, false);
                return new DescriptionHolder(view);

            case TYPE_STOCK:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_stock, viewGroup, false);
                return new StockHolder(view);

            default:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_stock, viewGroup, false);
                return new StockHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof StockHolder) {

            String name = stockList.get(i).getName();
            int volume = stockList.get(i).getVolume();
            double amount = stockList.get(i).getPrice().getAmount();

            ((StockHolder) viewHolder).tvName.setText(name);
            ((StockHolder) viewHolder).tvVolume.setText(FormatUtils.formatVolume(volume));
            ((StockHolder) viewHolder).tvAmount.setText(FormatUtils.formatAmount(amount));
        }

        //else it is description bar, no need to bind views

    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return TYPE_STOCK_DESCRIPTION;
        } else {

            return TYPE_STOCK;
        }
    }


    private void addDescriptionBar() {
        //dummy object
        stockList.add(new Stock());
    }

    //stock ViewHolder
    class StockHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvVolume, tvAmount;

        StockHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_stock_name);
            tvVolume = itemView.findViewById(R.id.tv_stock_volume);
            tvAmount = itemView.findViewById(R.id.tv_stock_amount);

        }
    }


    //description bar ViewHolder, with static data
    class DescriptionHolder extends RecyclerView.ViewHolder {
        DescriptionHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
