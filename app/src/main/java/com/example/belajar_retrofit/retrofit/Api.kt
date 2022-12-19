package com.example.belajar_retrofit.retrofit

import retrofit2.http.POST
import com.example.belajar_retrofit.datamodels.LoginResponse
import com.example.belajar_retrofit.datamodels.LogoutResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header

interface Api {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse?>?
    @POST("api/logout")
    fun logout(@Header("Authorization") token: String) : Call<LogoutResponse>
}