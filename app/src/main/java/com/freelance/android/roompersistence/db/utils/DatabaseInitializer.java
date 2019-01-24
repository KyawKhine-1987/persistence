package com.freelance.android.roompersistence.db.utils;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.freelance.android.roompersistence.db.AppDatabase;
import com.freelance.android.roompersistence.db.entities.Book;
import com.freelance.android.roompersistence.db.entities.Loan;
import com.freelance.android.roompersistence.db.entities.User;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by KyawKhine on 01/23/2019 5:00 PM.
 */


public class DatabaseInitializer {
    private static final String LOG_TAG = DatabaseInitializer.class.toString();

    private static final int Delay_Millis = 500;

    private static Book addBook(final AppDatabase db, final String Id, final String Title) {
        Log.i(LOG_TAG, "TEST: Book addBook() is called...");

        Book b = new Book();
        b.id = Id;
        b.title = Title;
        db.bookDao().insertBook(b);
        return b;
    }

    private static User addUser(final AppDatabase db, final String Id, final String Name, final String LastName, final int Age) {
        Log.i(LOG_TAG, "TEST: User addUser() is called...");

        User u = new User();
        u.id = Id;
        u.name = Name;
        u.lastName = LastName;
        u.age = Age;
        db.userDao().insertUser(u);
        return u;
    }

    private static void addLoan(final AppDatabase db, final String Id, final User user, final Book book, Date From, Date To) {
        Log.i(LOG_TAG, "TEST: addLoan() is called...");

        Loan l = new Loan();
        l.id = Id;
        l.user_Id = user.id;
        l.book_Id = book.id;
        l.startTime = From;
        l.endTime = To;
        db.loanDao().insertLoan(l);
    }

    public static void populateSync(@NonNull final AppDatabase db) {
        Log.i(LOG_TAG, "TEST: populateSync() is called...");

        populateWithTestData(db);
    }

    private static void populateWithTestData(AppDatabase db) {
        Log.i(LOG_TAG, "TEST: populateWithTestData() is called...");

        db.loanDao().deleteAll();
        db.userDao().deleteAll();
        db.bookDao().deleteAll();
        /* you must be written like that loan, user and book bcoz it depends on primary data which is solved this error "FOREIGN KEY constraint failed (code 787 SQLITE_CONSTRAINT_FOREIGNKEY)"*/

        User u1 = addUser(db, "1", "Jason", "Seaver", 40);
        User u2 = addUser(db, "2", "Mike", "Seaver", 12);
        addUser(db, "3", "Carol", "Seaver", 15);

        Book b1 = addBook(db, "1", "Dune");
        Book b2 = addBook(db, "2", "1984");
        Book b3 = addBook(db, "3", "The War of the Worlds.");
        Book b4 = addBook(db, "4", "Brave New World.");
        addBook(db, "5", "Foundation");

        try {
            Date today = getTodayPlusDays(0);
            Date yesterday = getTodayPlusDays(-1);
            Date twoDaysAgo = getTodayPlusDays(-2);
            Date lastWeek = getTodayPlusDays(-7);
            Date twoWeekAgo = getTodayPlusDays(-14);

            addLoan(db, "1", u1, b1, twoWeekAgo, lastWeek);
            Thread.sleep(Delay_Millis);
            addLoan(db, "2", u2, b1, lastWeek, yesterday);
            Thread.sleep(Delay_Millis);
            addLoan(db, "3", u2, b2, lastWeek, today);
            Thread.sleep(Delay_Millis);
            addLoan(db, "4", u2, b3, lastWeek, twoDaysAgo);
            Thread.sleep(Delay_Millis);
            addLoan(db, "5", u2, b4, lastWeek, today);
            Thread.sleep(Delay_Millis);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Date getTodayPlusDays(int daysAgo) {
        Log.i(LOG_TAG, "TEST: Date getTodayPlusDays() is called...");

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, daysAgo);
        return c.getTime();
    }

    public static void populateAsync(final AppDatabase db) {
        Log.i(LOG_TAG, "TEST: populateAsync() is called...");

        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private static final String LOG_TAG = PopulateDbAsync.class.toString();

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            Log.i(LOG_TAG, "TEST: PopulateDbAsync Constructor() is called...");

            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            Log.i(LOG_TAG, "TEST: doInBackground() is called...");

            populateWithTestData(mDb);
            return null;
        }
    }
}
