package com.mmnhsn.wallet.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mmnhsn.wallet.contracts.WalletContract;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by mamun on 12/9/17.
 */

public class AccountTransactionModel {

    private SQLiteDatabase db;

    public AccountTransactionModel(SQLiteDatabase database) {
        // Gets the data repository in write mode
        db = database;
    }

    public Long putData(AccountTransactionEntity entity) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(WalletContract.AccountTransaction.COLUMN_NAME_TITLE, entity.getTitle());
        values.put(WalletContract.AccountTransaction.COLUMN_NAME_TYPE, entity.getType());
        values.put(WalletContract.AccountTransaction.COLUMN_NAME_AMOUNT, entity.getAmount());
        values.put(WalletContract.AccountTransaction.COLUMN_NAME_TIMESTAMP, entity.getTimestamp());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(WalletContract.AccountTransaction.TABLE_NAME, null, values);

        return newRowId;
    }

    public List<AccountTransactionEntity> getList(long timestamp) {
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
        String[] selectionArgs = {String.valueOf(timestamp)};

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

        List<AccountTransactionEntity> entities = new ArrayList<AccountTransactionEntity>();

        while (cursor.moveToNext()) {
            AccountTransactionEntity entity = new AccountTransactionEntity();
            entity.setId(cursor.getLong(0));
            entity.setTitle(cursor.getString(1));
            entity.setType(cursor.getInt(2));
            entity.setAmount(cursor.getInt(3));
            entity.setTimestamp(cursor.getLong(4));
            entities.add(entity);
        }

        return entities;
    }

    public Integer updateData(AccountTransactionEntity entity) {
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(WalletContract.AccountTransaction.COLUMN_NAME_TITLE, entity.getTitle());
        values.put(WalletContract.AccountTransaction.COLUMN_NAME_TYPE, entity.getType());
        values.put(WalletContract.AccountTransaction.COLUMN_NAME_AMOUNT, entity.getAmount());
        values.put(WalletContract.AccountTransaction.COLUMN_NAME_TIMESTAMP, entity.getTimestamp());

        // Which row to update, based on the title
        String selection = WalletContract.AccountTransaction._ID + " = ?";
        String[] selectionArgs = {entity.getId().toString()};

        int count = db.update(
                WalletContract.AccountTransaction.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }

    public void deleteData(long itemId) {
        // Define 'where' part of query.
        String selection = WalletContract.AccountTransaction._ID + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {String.valueOf(itemId)};
        // Issue SQL statement.
        db.delete(WalletContract.AccountTransaction.TABLE_NAME, selection, selectionArgs);
    }
}
