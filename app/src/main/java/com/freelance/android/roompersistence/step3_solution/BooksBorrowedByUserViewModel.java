package com.freelance.android.roompersistence.step3_solution;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.freelance.android.roompersistence.db.AppDatabase;
import com.freelance.android.roompersistence.db.entities.Book;
import com.freelance.android.roompersistence.db.utils.DatabaseInitializer;

import java.util.List;

/**
 * Created by KyawKhine on 01/24/2019 6:35 PM.
 */


public class BooksBorrowedByUserViewModel extends AndroidViewModel {

    private static final String LOG_TAG = BooksBorrowedByUserViewModel.class.toString();

    public final LiveData<List<Book>> books;

    private AppDatabase mDb;

    public BooksBorrowedByUserViewModel(@NonNull Application application) {
        super(application);
        Log.i(LOG_TAG, "TEST: BooksBorrowedByUserViewModel Constructor() is called...");
        createDb();

        // Books is a LiveData object so updates are observed.
        books = mDb.bookDao().findBooksBorrowedByName("Mike");
    }

    public void createDb() {
        Log.i(LOG_TAG, "TEST: createDb() is called...");

        mDb = AppDatabase.getDatabase(this.getApplication());

        // Populate it with initial data
        DatabaseInitializer.populateAsync(mDb);
    }
}
