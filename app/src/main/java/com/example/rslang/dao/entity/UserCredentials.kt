package com.example.rslang.dao.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userCredentials")
data class UserCredentials(val token: String, val refreshToken: String, @PrimaryKey val userId: String)