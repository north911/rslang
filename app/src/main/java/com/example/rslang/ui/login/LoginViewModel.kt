package com.example.rslang.ui.login

import androidx.lifecycle.ViewModel
import com.example.rslang.dao.AppDatabase
import com.example.rslang.dao.AppFactory
import com.example.rslang.dao.entity.UserCredentials
import com.example.rslang.httpService.ApiClientImpl
import com.example.rslang.model.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

class LoginViewModel() : ViewModel() {

    private val apiClient: ApiClientImpl = ApiClientImpl()

    suspend fun sendLoginRequest(login: String, pwd: String): Deferred<Response<LoginResponse>> {
        return apiClient.sendLoginRequest(login, pwd)
    }

    fun searchForTokenInDB(db: AppDatabase): UserCredentials? {
        val userDao = db.userDao()
        val users = userDao?.getUserCredentials()
        return if (users?.size ==0) null else users?.get(0)
    }

    fun addUserToDb(
        db: AppDatabase,
        loginResponse: LoginResponse
    ) {
        val userDao = db.userDao()
        userDao?.insertUsers(
            UserCredentials(
                loginResponse.token,
                loginResponse.refreshToken,
                loginResponse.userId
            )
        )
    }
}