package com.freelance.android.roompersistence.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.freelance.android.roompersistence.db.entities.Book;
import com.freelance.android.roompersistence.db.entities.Loan;
import com.freelance.android.roompersistence.db.entities.User;


/**
 * Created by KyawKhine on 01/23/2019 5:01 PM.
 */

@Database(entities = {Book.class, User.class, Loan.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String LOG_TAG = AppDatabase.class.toString();

    private static AppDatabase instance;

    public abstract BookDao bookDao();

    public abstract UserDao userDao();

    public abstract LoanDao loanDao();

     public static AppDatabase getInMemoryDatabase(Context c) {
        Log.i(LOG_TAG, "TEST: AppDatabase getInMemoryDatabase() is called...");

        if (instance == null) {
            instance = Room.inMemoryDatabaseBuilder(c.getApplicationContext(), AppDatabase.class)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }

    //TODO List
    /*When the second times opened the app then will show this error "FOREIGN KEY constraint failed (code 787 SQLITE_CONSTRAINT_FOREIGNKEY"*/
    /*public static AppDatabase getDatabase(final Context c) {
        Log.i(LOG_TAG, "TEST: AppDatabase getInMemoryDatabase() is called...");

        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(c.getApplicationContext(),
                            AppDatabase.class, "library_database")
                            .allowMainThreadQueries() *//*solved this error "Cannot access database on the main thread since it may potentially lock the UI for a long period of time."*//*
                            .build();
                }
            }
        }
        return instance;
    }*/

    public static void destroyInstance() {
        Log.i(LOG_TAG, "TEST: destroyInstance() is called...");

        instance = null;
    }
}
