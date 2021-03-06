package com.freelance.android.roompersistence.step3_solution;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.freelance.android.roompersistence.R;
import com.freelance.android.roompersistence.db.entities.Book;

import java.util.List;

/**
 * Created by KyawKhine on 01/24/2019 6:35 PM.
 */


public class BooksBorrowedByUserActivity extends AppCompatActivity {

    private static final String LOG_TAG = BooksBorrowedByUserActivity.class.toString();

    private BooksBorrowedByUserViewModel mViewModel;

    @SuppressWarnings("unused")
    private TextView mBooksTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST: create() is called...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_activity);
        mBooksTextView = findViewById(R.id.books_tv);

        // Get a reference to the ViewModel for this screen.
        mViewModel = ViewModelProviders.of(this).get(BooksBorrowedByUserViewModel.class);

        // Update the UI whenever there's a change in the ViewModel's data.
        subscribeUIBooks();
    }

    private void subscribeUIBooks() {
        Log.i(LOG_TAG, "TEST: subscribeUIBooks() is called...");

        mViewModel.books.observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@NonNull final List<Book> books) {
                Log.i(LOG_TAG, "TEST: onChanged() is called...");

                showBooksInUI(books);
            }
        });
    }

    private void showBooksInUI(final @NonNull List<Book> books) {
        Log.i(LOG_TAG, "TEST: showBooksInUI() is called...");

        StringBuilder sb = new StringBuilder();

        for (Book b : books) {
            sb.append(b.title);
            sb.append("\n");
        }
        mBooksTextView.setText(sb.toString());
    }

    public void onRefreshBtClicked(View view) {
        mViewModel.createDb();
    }
}
