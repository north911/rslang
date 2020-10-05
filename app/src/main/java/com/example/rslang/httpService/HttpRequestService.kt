package com.example.rslang.httpService

import com.example.rslang.model.LoginRequest
import com.example.rslang.model.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface HttpRequestService {

    @Headers("Content-Type: application/json")
    @POST("/signin")
    fun getToken(@Body body: LoginRequest): Deferred<Response<LoginResponse>>
}