package com.example.rslang.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rslang.dao.entity.UserCredentials
import com.example.rslang.dao.service.UserCredentialsDAO

@Database(entities = [UserCredentials::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserCredentialsDAO?
}