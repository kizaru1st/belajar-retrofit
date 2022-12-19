package com.example.belajar_retrofit

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.belajar_retrofit.datamodels.LogoutResponse
import com.example.belajar_retrofit.retrofit.Api
import com.example.belajar_retrofit.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = applicationContext.getSharedPreferences("sharedPref", Context.MODE_PRIVATE) ?: return
        val token = sharedPref.getString("TOKEN", "")

        btnLogout.setOnClickListener {
            val retrofitClient = RetrofitClient.create()
            val callData = retrofitClient.logout("Bearer $token")
            callData.enqueue(object : Callback<LogoutResponse> {
                override fun onResponse(
                    call: Call<LogoutResponse>,
                    response: Response<LogoutResponse>
                ) {
//                    Log.d("logout : ", response.body().toString())
                    with(sharedPref.edit()) {
                        clear()
                        apply()
                    }
                    intent = Intent(this@MainActivity, Api::class.java)
                    startActivity(intent)
                    finish()
                }

                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Gaga", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}

