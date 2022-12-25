package com.example.belajar_retrofit.retrofit

import com.example.belajar_retrofit.datamodels.GetProfileResponse
import com.example.belajar_retrofit.datamodels.LoginResponse
import com.example.belajar_retrofit.datamodels.LogoutResponse
import com.example.belajar_retrofit.datamodels.User
import retrofit2.Call
import retrofit2.http.*

interface Login {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse?>?
    @POST("logout")
    fun logout(
        @Header("Authorization") token: String
    ) : Call<LogoutResponse>
    @GET("/api/me")
    fun profile(@Header("Authorization") token:String):Call<GetProfileResponse>
}