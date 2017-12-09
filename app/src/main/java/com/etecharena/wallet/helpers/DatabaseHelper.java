package com.etecharena.wallet.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.etecharena.wallet.contracts.WalletContract;

/**
 * Created by mamun on 12/9/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "wallet.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + WalletContract.AccountTransaction.TABLE_NAME + " (" +
                    WalletContract.AccountTransaction._ID + " INTEGER PRIMARY KEY," +
                    WalletContract.AccountTransaction.COLUMN_NAME_TITLE + " TEXT," +
                    WalletContract.AccountTransaction.COLUMN_NAME_TYPE + " INTEGER," +
                    WalletContract.AccountTransaction.COLUMN_NAME_AMOUNT + " INTEGER," +
                    WalletContract.AccountTransaction.COLUMN_NAME_TIMESTAMP + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + WalletContract.AccountTransaction.TABLE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
