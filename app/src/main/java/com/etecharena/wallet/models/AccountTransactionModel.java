package com.etecharena.wallet.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.etecharena.wallet.contracts.WalletContract;
import com.etecharena.wallet.helpers.DatabaseHelper;

/**
 * Created by mamun on 12/9/17.
 */

public class AccountTransactionModel {

    private SQLiteDatabase db;

    AccountTransactionModel(Context context) {
        // Gets the data repository in write mode
        db = new DatabaseHelper(context).getWritableDatabase();
    }

    public void putData(String type, String title) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(WalletContract.AccountTransaction.COLUMN_NAME_TITLE, title);
        values.put(WalletContract.AccountTransaction.COLUMN_NAME_TYPE, type);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(WalletContract.AccountTransaction.TABLE_NAME, null, values);
    }

    public void getData() {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                WalletContract.AccountTransaction._ID,
                WalletContract.AccountTransaction.COLUMN_NAME_TITLE,
                WalletContract.AccountTransaction.COLUMN_NAME_TYPE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = WalletContract.AccountTransaction.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = {"My Title"};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                WalletContract.AccountTransaction.COLUMN_NAME_TYPE + " DESC";

        Cursor cursor = db.query(
                WalletContract.AccountTransaction.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
    }
}
