package com.freelance.android.roompersistence.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.freelance.android.roompersistence.db.entities.Book;

/**
 * Created by KyawKhine on 01/23/2019 4:58 PM.
 */

@Dao
public interface BookDao {

    @Insert()
    void insertBook(Book book);

    @Query("DELETE FROM  Book;")
    void deleteAll();
}
