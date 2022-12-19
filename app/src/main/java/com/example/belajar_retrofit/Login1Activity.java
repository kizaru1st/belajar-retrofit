package com.example.belajar_retrofit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.belajar_retrofit.datamodels.LoginResponse;
import com.example.belajar_retrofit.retrofit.Api;

import retrofit2.Call;
import okhttp3.OkHttpClient;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login1Activity extends AppCompatActivity {
    EditText editEmail, editPassword;
    Button buttonLogin;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_login));
        cekLogin();
    }

    public void cekLogin() {
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String API_BASE_URL = "http://ptb-api.husnilkamil.my.id/";

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(new OkHttpClient.Builder().build())
                        .build();

                Api client = retrofit.create(Api.class);
                Call<LoginResponse> call = client.login(email, password);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        LoginResponse loginResponse = response.body();
                        if(loginResponse != null && loginResponse.getStatus() == "success") {
                            Toast.makeText(Login1Activity.this, "Sukses Login", Toast.LENGTH_SHORT).show();
                            String token = loginResponse.getAuthorisation().getToken();

                            SharedPreferences sharedPref = getSharedPreferences("prefs",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("TOKEN", token);
                            editor.apply();

                            Intent mainIntent = new Intent(Login1Activity.this, MainActivity.class);
                            startActivity(mainIntent);
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(Login1Activity.this, "Gagal Login", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

