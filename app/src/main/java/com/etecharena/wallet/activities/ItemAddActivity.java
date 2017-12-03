package com.etecharena.wallet.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.etecharena.wallet.R;
import com.etecharena.wallet.fragments.DatePickerFragment;

import java.util.Date;

public class ItemAddActivity extends AppCompatActivity implements DatePickerFragment.DatePickerFragmentListener {

    private ItemSaveTask mSaveTask = null;

    // UI references.
    private EditText mDateView;
    private EditText mTitleView;
    private EditText mAmountView;
    private View mProgressView;
    private View mItemFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_add);

        // Set up the login form.
        mDateView = (EditText) findViewById(R.id.pick_date);
        mTitleView = (EditText) findViewById(R.id.title);

        mAmountView = (EditText) findViewById(R.id.amount);
        mAmountView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    saveItem();
                    return true;
                }
                return false;
            }
        });

        Button mSaveButton = (Button) findViewById(R.id.save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveItem();
            }
        });
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(this);
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void saveItem() {
        if (mSaveTask != null) {
            return;
        }

        // Reset errors.
        mDateView.setError(null);
        mTitleView.setError(null);
        mAmountView.setError(null);

        // Store values at the time of the login attempt.
        String date = mDateView.getText().toString();
        String title = mTitleView.getText().toString();
        String amount = mAmountView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid amount, if the user entered one.
        if (!TextUtils.isEmpty(amount) && !isAmountValid(amount)) {
            mAmountView.setError(getString(R.string.error_invalid_amount));
            focusView = mAmountView;
            cancel = true;
        }

        // Check for a valid title.
        if (TextUtils.isEmpty(title)) {
            mTitleView.setError(getString(R.string.error_field_required));
            focusView = mTitleView;
            cancel = true;
        }

        // Check for a valid date.
        if (TextUtils.isEmpty(title)) {
            mDateView.setError(getString(R.string.error_field_required));
            focusView = mDateView;
            cancel = true;
        } else if (!isDateValid(date)) {
            mDateView.setError(getString(R.string.error_invalid_date));
            focusView = mDateView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mSaveTask = new ItemSaveTask(date, title, amount);
            mSaveTask.execute((Void) null);
        }
    }

    private boolean isDateValid(String date) {
        //TODO: Replace this with your own logic
        return date.contains("/");
    }

    private boolean isAmountValid(String amount) {
        //TODO: Replace this with your own logic
        return Integer.parseInt(amount) > 0;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mItemFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mItemFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mItemFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mItemFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onDateSet(Date date) {
        Log.d("Date", "date");
    }

    public class ItemSaveTask extends AsyncTask<Void, Void, Boolean> {

        private final String mDate;
        private final String mTitle;
        private final String mAmount;

        ItemSaveTask(String date, String title, String amount) {
            mDate = date;
            mTitle = title;
            mAmount = amount;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mSaveTask = null;
            showProgress(false);

            if (success) {
                finish();
                Intent accountPage = new Intent(ItemAddActivity.this, AccountActivity.class);
                startActivity(accountPage);
            } else {
                mAmountView.setError(getString(R.string.error_invalid_amount));
                mAmountView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mSaveTask = null;
            showProgress(false);
        }
    }
}
