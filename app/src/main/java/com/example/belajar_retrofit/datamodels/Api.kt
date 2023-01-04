package com.example.belajar_retrofit.datamodels

import com.example.belajar_retrofit.datamodels.*
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

    @GET("/api/my-internship/{id}/logbook")
    fun listlogbook(@Header("Authorization") token: String,
                    @Path("id") id:Int
    ):Call<LogbookResponse>

}