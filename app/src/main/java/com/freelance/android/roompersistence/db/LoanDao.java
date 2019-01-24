package com.freelance.android.roompersistence.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.freelance.android.roompersistence.db.entities.Loan;

/**
 * Created by KyawKhine on 01/23/2019 4:58 PM.
 */

@Dao
public interface LoanDao {

    @Insert()
    void insertLoan(Loan loan);

    @Query("DELETE FROM  Loan;")
    void deleteAll();
}
