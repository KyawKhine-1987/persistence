package com.freelance.android.roompersistence.db;

import android.arch.persistence.room.TypeConverter;
import android.util.Log;

import java.util.Date;//solved this error "Cannot figure out how to save this field into database. You can consider adding a type converter for it."

/**
 * Created by KyawKhine on 01/23/2019 5:01 PM.
 */
public class DateConverter {
    private static final String LOG_TAG = DateConverter.class.toString();

    @TypeConverter
    public static Date toDate(Long timestamp) {
        Log.i(LOG_TAG, "TEST: toDate() is called...");

        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        Log.i(LOG_TAG, "TEST: toTimestamp() is called...");

        return date == null ? null : date.getTime();
    }
}
