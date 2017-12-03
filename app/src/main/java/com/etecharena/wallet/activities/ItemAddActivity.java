package com.etecharena.wallet.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.etecharena.wallet.R;
import com.etecharena.wallet.fragments.DatePickerFragment;

import java.util.Date;

public class ItemAddActivity extends AppCompatActivity implements DatePickerFragment.DatePickerFragmentListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_add);

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
