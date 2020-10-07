package com.example.rslang.dao.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rslang.dao.entity.UserCredentials

@Dao
interface UserCredentialsDAO {

    @Query("SELECT * FROM userCredentials")
    fun getUserCredentials(): Array<UserCredentials>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(vararg users: UserCredentials)
}