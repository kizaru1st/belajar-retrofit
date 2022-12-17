package com.example.belajar_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import com.example.belajar_retrofit.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import com.example.belajar_retrofit.retrofit.Login
import com.example.belajar_retrofit.datamodels.LoginResponse
import android.widget.Toast
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.belajar_retrofit.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var editEmail: EditText
    lateinit  var editPassword: EditText
    lateinit  var buttonLogin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        cekLogin()
    }
    fun cekLogin() {
        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonLogin.setOnClickListener(View.OnClickListener {
            val API_BASE_URL = "http://ptb-api.husnilkamil.my.id/api/"
            var username = editEmail.getText().toString()
            var password = editPassword.getText().toString()
            val retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().build())
                .build()
            val client = retrofit.create(Login::class.java)
            val call = client.login(username, password)
            call!!.enqueue(object : Callback<LoginResponse?> {
                override fun onResponse(
                    call: Call<LoginResponse?>,
                    response: Response<LoginResponse?>
                ) {
                    val loginResponse = response.body()
                    Log.d("loginResponse", "login response error")
                    if (loginResponse != null) {
                        Toast.makeText(this@LoginActivity, "Sukses Login", Toast.LENGTH_SHORT)
                            .show()
                        val mainIntent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(mainIntent)
                    }
                    else {
                        Toast.makeText(this@LoginActivity, "Tidak ada input", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Gagal Login", Toast.LENGTH_SHORT).show()
                }
            })
        })
    }
}