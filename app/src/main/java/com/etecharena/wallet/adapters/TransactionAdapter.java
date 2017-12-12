package com.etecharena.wallet.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.etecharena.wallet.R;
import com.etecharena.wallet.models.AccountTransactionEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mamun on 12/9/17.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private List<AccountTransactionEntity> mDataset;

    public TransactionAdapter(List<AccountTransactionEntity> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction_card, parent, false));
    }

    @Override
    public void onBindViewHolder(TransactionAdapter.ViewHolder holder, int position) {
        AccountTransactionEntity mEntity = mDataset.get(position);

        holder.mTitleView.setText(mEntity.getTitle());
        holder.mAmountView.setText("Tk" + mEntity.getAmount());

        if (mEntity.getTimestamp() != null && mEntity.getTimestamp() > 0) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = formatter.format(mEntity.getTimestamp());
            holder.mDateView.setText(strDate);
        }

        if (mEntity.getType() != null && mEntity.getType() > 0) {
            holder.mCardView.setBackgroundColor(0xFF00FF00);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mCardView;
        public TextView mTitleView;
        public TextView mAmountView;
        public TextView mDateView;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (LinearLayout) itemView.findViewById(R.id.transaction_card);
            mTitleView = (TextView) itemView.findViewById(R.id.transaction_title);
            mAmountView = (TextView) itemView.findViewById(R.id.transaction_amount);
            mDateView = (TextView) itemView.findViewById(R.id.transaction_timestamp);
        }
    }
}
