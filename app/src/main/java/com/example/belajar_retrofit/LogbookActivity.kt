package com.example.belajar_retrofit

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.belajar_retrofit.datamodels.*
import com.example.belajar_retrofit.datamodels.LogbooksItem
import com.example.belajar_retrofit.datamodels.Api
import kotlinx.android.synthetic.main.activity_list_logbook.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogbookActivity : AppCompatActivity() {
    private val list = ArrayList<LogbooksItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_logbook)
        rvLogbook.setHasFixedSize(true)
        rvLogbook.layoutManager = LinearLayoutManager(this)
        initAction()
    }
    fun initAction() {
        listlog()
    }
    fun listlog(){
        val retro = Retro().getRetroClientInstance().create(Api::class.java)
        val sharePreference = getSharedPreferences("simpan", Context.MODE_PRIVATE)
        val token = "Bearer "+ sharePreference.getString("token", null)
        retro.listlogbook(token).enqueue(object : Callback<com.example.belajar_retrofit.datamodels.LogbookResponse> {
            override fun onResponse(
                call: Call<com.example.belajar_retrofit.datamodels.LogbookResponse>,
                response: Response<com.example.belajar_retrofit.datamodels.LogbookResponse>
            ) {
                if (response.body() != null){
                    response.body()?.let{list.addAll(list)}
                    val listLogbook = response.body()
                    val awal:List<LogbooksItem> = listLogbook!!.logbooks
                    val adapter = AdaptorLogbookActivity(awal)
                    rvLogbook.adapter = adapter
                    val sharePreference = getSharedPreferences("simpan", Context.MODE_PRIVATE)
                    val editor = sharePreference.edit()
                }
                else{
                    val text = "NT!"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()
                }
            }
            override fun onFailure(call: Call<com.example.belajar_retrofit.datamodels.LogbookResponse>, t: Throwable) {
                val text = "NT!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }
        })

    }

//    fun itemGetClicked(item: LogbooksItem){
//        Intent(this, DetailLogBook::class.java).also {
//            val id = item.id
//            val progress = item.progress
//            it.putExtra("EXRA_ID", id)
//            startActivity(it)
//        }
//    }
}