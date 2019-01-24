package com.freelance.android.roompersistence.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.freelance.android.roompersistence.db.entities.User;

import java.util.List;

/**
 * Created by KyawKhine on 01/23/2019 4:58 PM.
 */

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("DELETE FROM  User;")
    void deleteAll();

    /*@Query("SELECT * FROM User WHERE :age == :age")*/
    @Query("SELECT * FROM User WHERE :age == :age order by age;")
    List<User> findUsersYoungerThan(int age);

    @Query("SELECT * FROM User WHERE age < :age;")
    List<User> findUsersYoungerThanSolution(int age);
}
