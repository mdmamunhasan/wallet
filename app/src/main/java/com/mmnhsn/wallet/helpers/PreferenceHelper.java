package com.mmnhsn.wallet.helpers;

import android.content.Context;

import com.mmnhsn.wallet.utils.PreferenceUtil;

/**
 * Created by mamun on 12/2/17.
 */

public class PreferenceHelper extends PreferenceUtil {
    private static PreferenceHelper instance;

    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";

    private PreferenceHelper(Context context) {
        super(context);
    }

    synchronized public static PreferenceHelper onInstance(Context context) {
        if (instance == null) {
            instance = new PreferenceHelper(context);
        }
        return instance;
    }

    public void saveUserEmail(String value) {
        writeString(USER_EMAIL, value);
    }

    public String getUserEmail() {
        return readString(USER_EMAIL, null);
    }

    public void saveUserPassword(String value) {
        writeString(USER_PASSWORD, value);
    }

    public String getUserPassword() {
        return readString(USER_PASSWORD, null);
    }
}
