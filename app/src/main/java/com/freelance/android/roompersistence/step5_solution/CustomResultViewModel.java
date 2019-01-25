package com.freelance.android.roompersistence.step5_solution;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.util.Log;

import com.freelance.android.roompersistence.db.AppDatabase;
import com.freelance.android.roompersistence.db.entities.LoanWithUserAndBook;
import com.freelance.android.roompersistence.db.utils.DatabaseInitializer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by KyawKhine on 01/25/2019 4:13 PM.
 */


public class CustomResultViewModel extends AndroidViewModel {

    private static final String LOG_TAG = CustomResultViewModel.class.toString();

    private LiveData<String> mLoansResult;
    private AppDatabase mDb;

    public CustomResultViewModel(Application application) {
        super(application);

        Log.i(LOG_TAG, "TEST: CustomResultViewModel Constructor() is called...");
    }

    public LiveData<String> getLoansResult() {
        Log.i(LOG_TAG, "TEST: getLoansResult() is called...");

        return mLoansResult;
    }

    public void createDb() {
        Log.i(LOG_TAG, "TEST: createDb() is called...");

        mDb = AppDatabase.getDatabase(getApplication());
        // Populate it with initial data
        DatabaseInitializer.populateAsync(mDb);
        // Receive changes
        subscribeToDbChanges();
    }

    private void subscribeToDbChanges() {
        Log.i(LOG_TAG, "TEST: subscribeToDbChanges() is called...");

        LiveData<List<LoanWithUserAndBook>> loans = mDb.loanDao().findLoansByNameAfter("Mike", getYesterdayDate());

        mLoansResult = Transformations.map(loans, new Function<List<LoanWithUserAndBook>, String>() {
            @Override
            public String apply(List<LoanWithUserAndBook> loansWithUserAndBook) {
                Log.i(LOG_TAG, "TEST: apply(List<LoanWithUserAndBook> loansWithUserAndBook) is called...");

                StringBuilder sb = new StringBuilder();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);

                for (LoanWithUserAndBook l : loansWithUserAndBook) {
                    sb.append(String.format("%s\n (Returned: %s)\n",
                            l.bookTitle,
                            sdf.format(l.endTime)));
                }
                return sb.toString();
            }
        });
    }

    @SuppressWarnings("unused")
    private Date getYesterdayDate() {
        Log.i(LOG_TAG, "TEST: getYesterdayDate() is called...");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }
}
