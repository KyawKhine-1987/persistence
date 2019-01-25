package com.freelance.android.roompersistence.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.freelance.android.roompersistence.db.entities.Loan;
import com.freelance.android.roompersistence.db.entities.LoanWithUserAndBook;

import java.util.Date;
import java.util.List;

/**
 * Created by KyawKhine on 01/23/2019 4:58 PM.
 */

@Dao
public interface LoanDao {

    @Insert()
    void insertLoan(Loan loan);

    @Query("DELETE FROM  Loan;")
    void deleteAll();

    @Query("select l.id, b.title, u.name, l.startTime, l.endTime from Loan as l " +
            "Inner Join Book as b On l.book_id = b.id " +
            "Inner Join User as u On l.user_id = u.id ")
    LiveData<List<LoanWithUserAndBook>> findAllWithUserAndBook();

    @Query("select l.id, b.title, u.name, l.startTime, l.endTime from Book as b " +
            "Inner Join Loan as l On l.book_id = b.id " +
            "Inner Join User as u On l.user_id = u.id " +
            "Where u.name like :username " +
            "And l.endTime > :after ;")
    LiveData<List<LoanWithUserAndBook>> findLoansByNameAfter(String username, Date after);
}
