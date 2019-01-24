package com.freelance.android.roompersistence.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.freelance.android.roompersistence.db.DateConverter;

import java.util.Date;

/**
 * Created by KyawKhine on 01/23/2019 4:57 PM.
 */

@Entity(foreignKeys = {
        @ForeignKey(entity = Book.class,
                parentColumns = "id",
                childColumns = "book_id"),

        @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "user_id")
})
@TypeConverters(DateConverter.class)
public class Loan {

    @PrimaryKey
    @NonNull
    public String id;

    public Date startTime;

    public Date endTime;

    @ColumnInfo(name = "book_id")
    public String bookId;

    @ColumnInfo(name = "user_id")
    public String userId;

}
