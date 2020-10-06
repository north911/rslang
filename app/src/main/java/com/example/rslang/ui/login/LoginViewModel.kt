package com.example.rslang.ui.login

import androidx.lifecycle.ViewModel
import com.example.rslang.httpService.ApiClientImpl
import com.example.rslang.model.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

class LoginViewModel() : ViewModel() {

    private  val apiClient: ApiClientImpl = ApiClientImpl()

    suspend fun sendLoginRequest(login: String, pwd: String): Deferred<Response<LoginResponse>> {
        return apiClient.sendLoginRequest(login, pwd)
    }
}