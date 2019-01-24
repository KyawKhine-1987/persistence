package com.freelance.android.roompersistence.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.freelance.android.roompersistence.db.entities.Book;

import java.util.List;

/**
 * Created by KyawKhine on 01/23/2019 4:58 PM.
 */

@Dao
public interface BookDao {

    @Insert()
    void insertBook(Book book);

    @Query("DELETE FROM  Book;")
    void deleteAll();

    //step2
    @Query("select * from Book as b " +
            "Inner Join Loan as l on l.book_id = b.id " +
            "Inner Join User as u on u.id = l.user_id " +
            "where u.name like :userName;")
    List<Book> findBooksBorrowedByNameSync(String userName);

    //step3
    @Query("select * from Book as b " +
            "Inner Join Loan as l on l.book_id = b.id " +
            "Inner Join User as u on u.id = l.user_id " +
            "where u.name like :userName;")
    LiveData<List<Book>> findBooksBorrowedByName(String userName);
}
