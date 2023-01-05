package com.example.belajar_retrofit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.belajar_retrofit.datamodels.Api
import com.example.belajar_retrofit.datamodels.Config
import com.example.belajar_retrofit.datamodels.DetailLogBookResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailLogBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_log_book)
        val sharedTokennya = getSharedPreferences("sharedpref", Context.MODE_PRIVATE)?: return
        val logbookpref = getSharedPreferences("logbookpref",Context.MODE_PRIVATE)?:return

        val token = sharedTokennya.getString("token",null)
        val id = sharedTokennya.getInt("id",5)
        val idl = logbookpref.getString("id_logbook",null)
        Log.d("Detail-logbook-debug","respon "+idl.toString())

        val client: Api = Config().getService()
        val call: Call<DetailLogBookResponse> = client.detailLogbookMaha(token = "Bearer"+token,id,idl)
        Log.d("Detail-logbook-debug","respon "+token.toString())
        call.enqueue(object : Callback<DetailLogBookResponse> {
            override fun onResponse(
                call: Call<DetailLogBookResponse>,
                response: Response<DetailLogBookResponse>
            ) {
                val respon = response.body()
                if(respon!=null){
                    val judulKegiatan : TextView = findViewById(R.id.tvKegiatanList)
                    val kegiatan = respon.logbook?.activities
                    judulKegiatan.text = kegiatan
                }
            }
            override fun onFailure(call: Call<DetailLogBookResponse>, t: Throwable) {
                Log.d("detail-debug", t.localizedMessage)
            }

        })
    }
}