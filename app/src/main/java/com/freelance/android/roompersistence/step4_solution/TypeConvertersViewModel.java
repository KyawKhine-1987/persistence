package com.freelance.android.roompersistence.step4_solution;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.freelance.android.roompersistence.db.AppDatabase;
import com.freelance.android.roompersistence.db.entities.Book;
import com.freelance.android.roompersistence.db.utils.DatabaseInitializer;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by KyawKhine on 01/24/2019 10:19 PM.
 */


public class TypeConvertersViewModel extends AndroidViewModel {

    private static final String LOG_TAG = TypeConvertersViewModel.class.toString();

    private LiveData<List<Book>> mBooks;
    private AppDatabase mDb;

    public TypeConvertersViewModel(@NonNull Application application) {
        super(application);

        Log.i(LOG_TAG, "TEST: TypeConvertersViewModel Constructor() is called...");
        createDb();
    }

    public void createDb() {
        Log.i(LOG_TAG, "TEST: createDb() is called...");

        mDb = AppDatabase.getDatabase(this.getApplication());
        // Populate it with initial data
        DatabaseInitializer.populateAsync(mDb);
        // Receive changes
        subscribeToDbChanges();
    }

    private void subscribeToDbChanges() {
        Log.i(LOG_TAG, "TEST: subscribeToDbChanges() is called...");

        //TODO: for convert timestamp to Date which is correctly or not, shouble be troubleshooting in Sqlite
        // Books is a LiveData object so updates are observed.
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        Date yesterday = c.getTime();
        //Log.d("Date", yesterday.toString());
        mBooks = mDb.bookDao().findBooksBorrowedByNameAfter("Mike", yesterday);
    }

    public LiveData<List<Book>> getBooks() {
        Log.i(LOG_TAG, "TEST: getBooks() is called...");

        return mBooks;
    }
}
