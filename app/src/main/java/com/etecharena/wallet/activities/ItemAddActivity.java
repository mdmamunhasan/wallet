package com.etecharena.wallet.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    // UI references.
    private EditText mTitleView;
    private EditText mAmountView;
    private View mProgressView;
    private View mItemFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_add);

        // Set up the login form.
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
        Log.d("Save", "save");
    }

    @Override
    public void onDateSet(Date date) {
        Log.d("Date", "date");
    }
}
