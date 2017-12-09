package com.etecharena.wallet.contracts;

import android.provider.BaseColumns;

/**
 * Created by mamun on 12/9/17.
 */

public class WalletContract {
    private WalletContract() {
    }

    /* Inner class that defines the table contents */
    public static class AccountEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_AMOUNT = "amount";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
    }
}
