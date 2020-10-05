package com.example.rslang.httpService

import com.example.rslang.model.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface ApiClient {
    suspend fun sendLoginRequest(login:String, pwd: String) : Deferred<Response<LoginResponse>>
}