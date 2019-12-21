package com.example.servicebookingandroid;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseActivity extends AppCompatActivity {
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://servicebookingbackend-env.c9mdrevktd.us-east-2.elasticbeanstalk.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
