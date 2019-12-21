package com.example.servicebookingandroid.Auth;

import android.content.Intent;

import com.example.servicebookingandroid.BaseActivity;
import com.example.servicebookingandroid.DashBoard.MainActivity;
import com.example.servicebookingandroid.Model.UserDto;
import com.example.servicebookingandroid.Services.AuthService;


public class AuthBaseActivity extends BaseActivity {
    public static AuthService authService = retrofit.create(AuthService.class);

    public static String token = null;
    public static UserDto user = null;

    @Override
    protected void onStart() {
        super.onStart();
        if (user != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
