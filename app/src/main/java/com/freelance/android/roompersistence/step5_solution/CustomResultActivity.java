package com.freelance.android.roompersistence.step5_solution;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.freelance.android.roompersistence.R;

/**
 * Created by KyawKhine on 01/25/2019 4:14 PM.
 */


public class CustomResultActivity extends AppCompatActivity {

    private static final String LOG_TAG = CustomResultActivity.class.toString();

    private CustomResultViewModel mShowUserViewModel;

    private TextView mBooksTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST: onCreate() is called...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_activity);
        mBooksTextView = findViewById(R.id.books_tv);
        mShowUserViewModel = ViewModelProviders.of(this).get(CustomResultViewModel.class);

        populateDb();

        subscribeUILoans();
    }

    private void subscribeUILoans() {
        Log.i(LOG_TAG, "TEST: subscribeUILoans() is called...");

        mShowUserViewModel.getLoansResult().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String result) {
                mBooksTextView.setText(result);
            }
        });
    }

    private void populateDb() {
        Log.i(LOG_TAG, "TEST: populateDb() is called...");

        mShowUserViewModel.createDb();
    }

    public void onRefreshBtClicked(View view) {
        Log.i(LOG_TAG, "TEST: onRefreshBtClicked() is called...");

        populateDb();
        subscribeUILoans();
    }
}
