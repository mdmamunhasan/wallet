package com.etecharena.wallet.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.etecharena.wallet.R;
import com.etecharena.wallet.adapters.TransactionAdapter;
import com.etecharena.wallet.fragments.ItemDialogFragment;
import com.etecharena.wallet.helpers.DatabaseHelper;
import com.etecharena.wallet.models.AccountTransactionModel;

public class AccountActivity extends AppCompatActivity {

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
                finish();
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
        String[] mDataSet = {"Hello", "Good"};
        Cursor res = transactionModel.getData();
        if (res.getCount() > 0) {
            res.moveToNext();
            String title = res.getString(1);
            Log.d("Read", title);
        } else {
            Log.d("Read", "No Data Found");
        }

        // specify an adapter (see also next example)
        mAdapter = new TransactionAdapter(mDataSet);
        mRecyclerView.setAdapter(mAdapter);
    }
}
