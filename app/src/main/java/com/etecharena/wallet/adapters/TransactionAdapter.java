package com.etecharena.wallet.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

/**
 * Created by mamun on 12/9/17.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionVH> implements Filterable {

    @Override
    public TransactionVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TransactionVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class TransactionVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TransactionVH(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
