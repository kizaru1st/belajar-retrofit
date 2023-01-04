//package com.example.belajar_retrofit;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//
//import com.example.belajar_retrofit.datamodels.Api;
//import com.example.belajar_retrofit.datamodels.ListLogbookResponse;
//import com.example.belajar_retrofit.datamodels.LogbooksItem;
//
//import java.util.List;
//
//import okhttp3.OkHttpClient;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class ListLogbookActivity extends AppCompatActivity {
//
//    private RecyclerView rvListLogbook;
//    private ListLogbookAdapter adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_logbook);
//
//        SharedPreferences sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE);
//        String token = sharedPref.getString("TOKEN", "");
//
//        rvListLogbook = findViewById(R.id.rvLogbook);
//        rvListLogbook.setLayoutManager(new LinearLayoutManager(this));
//
//        adapter = new ListLogbookAdapter();
//        rvListLogbook.setAdapter(adapter);
//
//        String API_BASE_URL = "http://ptb-api.husnilkamil.my.id/api/";
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(API_BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(new OkHttpClient.Builder().build())
//                .build();
//
//        Api client = retrofit.create(Api.class);
//        Call<ListLogbookResponse> call = client.getListLogbook("Bearer $token");
//        call.enqueue(new Callback<ListLogbookResponse>() {
//            @Override
//            public void onResponse(Call<ListLogbookResponse> call, Response<ListLogbookResponse> response) {
//                ListLogbookResponse listLogbookResponse = response.body();
//                if (listLogbookResponse != null) {
//                    List<LogbooksItem> logbooks = listLogbookResponse.getLogbooks();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ListLogbookResponse> call, Throwable t) {
//
//            }
//        });
//    }
//}