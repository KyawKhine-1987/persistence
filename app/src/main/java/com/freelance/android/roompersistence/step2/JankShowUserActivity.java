package com.freelance.android.roompersistence.step2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.freelance.android.roompersistence.R;
import com.freelance.android.roompersistence.db.AppDatabase;
import com.freelance.android.roompersistence.db.entities.Book;
import com.freelance.android.roompersistence.db.utils.DatabaseInitializer;

import java.util.List;

/**
 * Created by KyawKhine on 01/24/2019 1:20 PM.
 */


public class JankShowUserActivity extends AppCompatActivity {

    private static final String LOG_TAG = JankShowUserActivity.class.toString();

    private AppDatabase mDb;

    private TextView mBooksTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST: onCreate() is called...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_activity);
        mBooksTextView = findViewById(R.id.books_tv);
        // Note: Db references should not be in an activity.
        /*mDb = AppDatabase.getInMemoryDatabase(this);*/
        mDb = AppDatabase.getDatabase(this);

        populateDb();
        fetchData();
    }

    @Override
    protected void onDestroy() {
        Log.i(LOG_TAG, "TEST: onDestroy() is called...");

        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    private void fetchData() {
        Log.i(LOG_TAG, "TEST: fetchData() is called...");

        List<Book> books = mDb.bookDao().findBooksBorrowedByNameSync("Mike");
        showListInUI(books, mBooksTextView);
    }

    private void showListInUI(final @NonNull List<Book> books, final TextView mBooksTextView) {
        Log.i(LOG_TAG, "TEST: showListInUI() is called...");

        StringBuilder sb = new StringBuilder();
        for (Book b : books) {
            sb.append(b.title);
            sb.append("\n");
        }
        mBooksTextView.setText(sb.toString());
    }

    private void populateDb() {
        Log.i(LOG_TAG, "TEST: populateDb() is called...");

        DatabaseInitializer.populateSync(mDb);
    }

    public void onRefreshBtClicked(View view) {
        Log.i(LOG_TAG, "TEST: onRefreshBtClicked() is called...");

        mBooksTextView.setText("");
        fetchData();
    }
}
