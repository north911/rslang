package com.example.rslang.dao

import android.content.Context
import androidx.room.Room

class AppFactory {
    companion object {
        fun createDatabaseObject(context: Context?): AppDatabase {
            return Room.databaseBuilder(
                context!!,
                AppDatabase::class.java, "rslangDB"
            ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
        }
    }
}