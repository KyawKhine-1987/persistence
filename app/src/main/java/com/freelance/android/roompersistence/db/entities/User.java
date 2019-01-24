package com.freelance.android.roompersistence.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by KyawKhine on 01/23/2019 4:57 PM.
 */

@Entity
public class User {
    @PrimaryKey
    @NonNull
    public String id;

    public int age;

    public String name;

    public String lastName;
}
