package com.example.belajar_retrofit

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.belajar_retrofit.databinding.ActivityListLogbookBinding
import com.example.belajar_retrofit.databinding.ActivityLogBinding
import com.example.belajar_retrofit.datamodels.Api
import com.example.belajar_retrofit.datamodels.Config
import com.example.belajar_retrofit.datamodels.LogbookResponse
import com.example.belajar_retrofit.datamodels.LogbooksItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogActivity : AppCompatActivity() {
    lateinit var adapter: LogAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var binding: ActivityListLogbookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListLogbookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter: LogAdapter = LogAdapter()


        val sharedToken = applicationContext.getSharedPreferences("sharedPref", Context.MODE_PRIVATE) ?: return
        val token = sharedToken.getString("TOKEN", "")
        val sharedPref = getSharedPreferences("mar", Context.MODE_PRIVATE) ?: return
        val id = sharedToken.getInt("ID", 5)

        val data = ArrayList<LogbooksItem>()
        recyclerView = findViewById(R.id.rvLogbook)

        Log.d("list-book-debug", token.toString())
        val client: Api = Config().getService()
        val call: Call<LogbookResponse> = client.listlogbook(token = "Bearer " + token, id)

        call.enqueue(object : Callback<LogbookResponse> {
            override fun onResponse(
                call: Call<LogbookResponse>,
                response: Response<LogbookResponse>
            ) {
                val respon = response.body()
                if (respon != null) {
                    val list: List<LogbooksItem> = respon.logbooks as List<LogbooksItem>
                    adapter.setlistLogbook(list)
                    Log.d("list-book-debug", list.toString())
                }
                Log.d("list-book-debug", respon?.logbooks?.size.toString())
                Log.d("list-book-debug", "respon : " + respon?.logbooks.toString())

                adapter.setOnClickListener(object : LogAdapter.onClickListener{
                    override fun onItemClick(position: Int) {
                        val position = respon?.logbooks?.get(position)
                        val sharedPref = getSharedPreferences("logbookpref", MODE_PRIVATE)?:return
                        with(sharedPref.edit()){
                            putString("id_logbook",position?.id.toString())
                            apply()
                        }
                        Log.d("Detail-logbook",position.toString())
                        val intent = Intent(this@LogActivity,DetailLogBookActivity::class.java)
//
                        startActivity(intent)
                    }
                })
            }

            override fun onFailure(call: Call<LogbookResponse>, t: Throwable) {
                val text = "NT!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
                Log.d("list-book-debug", t.localizedMessage)
            }

        })
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
//        adapter.setOnClickListener(object : LogAdapter.onClickListener{
//            override fun onItemClick(position: Int) {
//                val intent = Intent(this@LogActivity,DetailLogBookActivity::class.java)
//                startActivity(intent)
//            }
//        })
    }

}