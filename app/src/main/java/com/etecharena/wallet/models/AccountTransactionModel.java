package com.etecharena.wallet.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.etecharena.wallet.contracts.WalletContract;
import com.etecharena.wallet.helpers.DatabaseHelper;

import java.util.Objects;

/**
 * Created by mamun on 12/9/17.
 */

public class AccountTransactionModel {

    private SQLiteDatabase db;

    public AccountTransactionModel(SQLiteDatabase database) {
        // Gets the data repository in write mode
        db = database;
    }

    public Long putData(String title, Integer type, Integer amount, Long timestamp) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(WalletContract.AccountTransaction.COLUMN_NAME_TITLE, title);
        values.put(WalletContract.AccountTransaction.COLUMN_NAME_TYPE, type);
        values.put(WalletContract.AccountTransaction.COLUMN_NAME_AMOUNT, amount);
        values.put(WalletContract.AccountTransaction.COLUMN_NAME_TIMESTAMP, type);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(WalletContract.AccountTransaction.TABLE_NAME, null, values);

        return newRowId;
    }

    public Cursor getData() {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                WalletContract.AccountTransaction._ID,
                WalletContract.AccountTransaction.COLUMN_NAME_TITLE,
                WalletContract.AccountTransaction.COLUMN_NAME_TYPE,
                WalletContract.AccountTransaction.COLUMN_NAME_AMOUNT,
                WalletContract.AccountTransaction.COLUMN_NAME_TIMESTAMP
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = WalletContract.AccountTransaction.COLUMN_NAME_TIMESTAMP + " > ?";
        String[] selectionArgs = {"0"};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                WalletContract.AccountTransaction.COLUMN_NAME_TIMESTAMP + " DESC";

        Cursor cursor = db.query(
                WalletContract.AccountTransaction.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        return cursor;
    }

    public void updateData(String title) {
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(WalletContract.AccountTransaction.COLUMN_NAME_TITLE, title);

        // Which row to update, based on the title
        String selection = WalletContract.AccountTransaction.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = {"MyTitle"};

        int count = db.update(
                WalletContract.AccountTransaction.TABLE_NAME,
                values,
                selection,
                selectionArgs);

    }

    public void deleteData() {
        // Define 'where' part of query.
        String selection = WalletContract.AccountTransaction.COLUMN_NAME_TITLE + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {"MyTitle"};
        // Issue SQL statement.
        db.delete(WalletContract.AccountTransaction.TABLE_NAME, selection, selectionArgs);
    }
}
