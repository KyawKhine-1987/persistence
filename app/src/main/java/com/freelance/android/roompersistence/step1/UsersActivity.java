package com.freelance.android.roompersistence.step1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.freelance.android.roompersistence.R;
import com.freelance.android.roompersistence.db.AppDatabase;
import com.freelance.android.roompersistence.db.entities.User;
import com.freelance.android.roompersistence.db.utils.DatabaseInitializer;

import java.util.List;
import java.util.Locale;

public class UsersActivity extends AppCompatActivity {

    private static final String LOG_TAG = UsersActivity.class.toString();

    private AppDatabase mDb;

    private TextView mYoungUsersTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST: onCreate() is called...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_activity1);

        mYoungUsersTextView = findViewById(R.id.young_users_tv);
        /*mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());*/
        mDb = AppDatabase.getDatabase(getApplicationContext());

        populateDb();
        fetchData();
    }

    @Override
    protected void onDestroy() {
        Log.i(LOG_TAG, "TEST: onDestroy() is called...");

        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    private void populateDb() {
        Log.i(LOG_TAG, "TEST: populateDb() is called...");

        DatabaseInitializer.populateSync(mDb);
    }

    private void fetchData() {
        Log.i(LOG_TAG, "TEST: fetchData() is called...");

        StringBuilder sb = new StringBuilder();
        List<User> youngUsers = mDb.userDao().findUsersYoungerThan(35);
        for (User youngUser : youngUsers) {

            sb.append(String.format(Locale.US,
                    "%s, %s (%d)\n",
                    youngUser.lastName,
                    youngUser.name,
                    youngUser.age));
        }
        mYoungUsersTextView.setText(sb.toString());
    }
}
