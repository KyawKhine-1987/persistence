package com.freelance.android.roompersistence.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.TypeConverters;

import com.freelance.android.roompersistence.db.DateConverter;

import java.util.Date;

/**
 * Created by KyawKhine on 01/23/2019 5:01 PM.
 */


public class LoanWithUserAndBook {

    public String id;

    @ColumnInfo(name = "title")
    public String bookTitle;

    @ColumnInfo(name = "name")
    public String username;

    @TypeConverters(DateConverter.class)
    public Date startTime;

    @TypeConverters(DateConverter.class)
    public Date endTime;
}
