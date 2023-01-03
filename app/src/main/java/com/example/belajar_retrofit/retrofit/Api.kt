package com.example.belajar_retrofit.retrofit

import com.example.belajar_retrofit.datamodels.GetProfileResponse
import com.example.belajar_retrofit.datamodels.ListLogbookResponse
import com.example.belajar_retrofit.datamodels.LoginResponse
import com.example.belajar_retrofit.datamodels.LogoutResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {
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

    @GET("me")
    fun profile(@Header("Authorization") token:String):Call<GetProfileResponse>

    @GET("theses/309/logbooks")
    fun listLogbook(@Header("Authorization") token:String):Call<ListLogbookResponse>
}