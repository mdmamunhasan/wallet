package com.etecharena.wallet.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.etecharena.wallet.R;
import com.etecharena.wallet.adapters.TransactionAdapter;
import com.etecharena.wallet.contracts.WalletContract;
import com.etecharena.wallet.fragments.ItemDialogFragment;
import com.etecharena.wallet.helpers.DatabaseHelper;
import com.etecharena.wallet.models.AccountTransactionEntity;
import com.etecharena.wallet.models.AccountTransactionModel;

import java.util.List;

public class AccountActivity extends AppCompatActivity implements TransactionAdapter.TransactionItemClickListener {

    public SQLiteDatabase db;
    public AccountTransactionModel transactionModel;

    // UI references.
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_transactions);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accountPage = new Intent(AccountActivity.this, ItemAddActivity.class);
                startActivity(accountPage);
            }
        });

        db = new DatabaseHelper(this).getWritableDatabase();
        transactionModel = new AccountTransactionModel(db);

        showData();
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

    public void showItemDialog() {
        ItemDialogFragment itemDialog = new ItemDialogFragment();
        itemDialog.show(getSupportFragmentManager(), "updateItem");
    }

    public void showData() {
        List<AccountTransactionEntity> mDataSet = transactionModel.getList();
        // specify an adapter (see also next example)
        mAdapter = new TransactionAdapter(mDataSet, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onTransactionItemClick(View view, int position, AccountTransactionEntity entity) {
        Intent accountPage = new Intent(AccountActivity.this, ItemAddActivity.class);

        accountPage.putExtra(WalletContract.AccountTransaction._ID, entity.getId());
        accountPage.putExtra(WalletContract.AccountTransaction.COLUMN_NAME_TITLE, entity.getTitle());
        accountPage.putExtra(WalletContract.AccountTransaction.COLUMN_NAME_TYPE, entity.getType());
        accountPage.putExtra(WalletContract.AccountTransaction.COLUMN_NAME_AMOUNT, entity.getAmount());
        accountPage.putExtra(WalletContract.AccountTransaction.COLUMN_NAME_TIMESTAMP, entity.getTimestamp());

        startActivity(accountPage);
    }
}
