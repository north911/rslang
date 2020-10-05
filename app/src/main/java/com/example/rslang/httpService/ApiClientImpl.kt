package com.example.rslang.httpService

import com.example.rslang.model.LoginRequest
import com.example.rslang.model.LoginResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClientImpl : ApiClient {

    private val RSLANG_BASE_URL = "https://rslang-team16-arcanar7.web.app/"
    private val RSLANG_BASE_URL_LOGIN = "https://rslang-team16.herokuapp.com/"


    override suspend fun sendLoginRequest(login: String, pwd: String): Deferred<Response<LoginResponse>> {
        val service = initializeRetrofit()
        return service.getToken(LoginRequest(login, pwd))
    }

    private fun initializeRetrofit(): HttpRequestService {
        val retrofit: Retrofit = initializeLoginRetrofitInstance()
        return retrofit.create(HttpRequestService::class.java)
    }

    private fun initializeLoginRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RSLANG_BASE_URL_LOGIN)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}