package com.example.belajar_retrofit.retrofit

import retrofit2.http.POST
import com.example.belajar_retrofit.datamodels.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

interface Login {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse?>?
}