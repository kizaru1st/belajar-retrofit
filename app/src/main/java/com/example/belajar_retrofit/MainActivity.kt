package com.example.belajar_retrofit

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.belajar_retrofit.datamodels.GetProfileResponse
import com.example.belajar_retrofit.datamodels.LogoutResponse
import com.example.belajar_retrofit.datamodels.Api
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = applicationContext.getSharedPreferences("sharedPref", Context.MODE_PRIVATE) ?: return
        val token = sharedPref.getString("TOKEN", "")
        val API_BASE_URL = "http://ptb-api.husnilkamil.my.id/api/"
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
        val clientGetProfile = retrofit.create(Api::class.java)
        val callGetProfile = clientGetProfile.profile("Bearer $token")
        callGetProfile!!.enqueue(object : Callback<GetProfileResponse?> {
            override fun onResponse(
                call: Call<GetProfileResponse?>,
                response: Response<GetProfileResponse?>
            ) {
                val respon = response.body()
                val getNama = respon?.name
                tvNama.setText(getNama)
            }
            override fun onFailure(call: Call<GetProfileResponse?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Gaga", Toast.LENGTH_SHORT).show()
            }
        })

        btnSelesaiKp.setOnClickListener {
            intent = Intent(this@MainActivity, LogActivity::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            val client = retrofit.create(Api::class.java)
            val call = client.logout("Bearer $token")
            call!!.enqueue(object : Callback<LogoutResponse?> {
                override fun onResponse(
                    call: Call<LogoutResponse?>,
                    response: Response<LogoutResponse?>
                ) {
                    Log.d("logout : ", response.body().toString())
                    with(sharedPref.edit()) {
                        clear()
                        apply()
                    }
                    intent = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                override fun onFailure(call: Call<LogoutResponse?>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Gaga", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}

