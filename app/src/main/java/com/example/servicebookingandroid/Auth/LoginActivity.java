package com.example.servicebookingandroid.Auth;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.servicebookingandroid.DashBoard.MainActivity;
import com.example.servicebookingandroid.Model.APIRequests.LoginRequest;
import com.example.servicebookingandroid.Model.Response.JWTLoginSucessReponse;
import com.example.servicebookingandroid.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AuthBaseActivity {

    private EditText ed_email, ed_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_email=(EditText)findViewById(R.id.Email);
        ed_password=(EditText)findViewById(R.id.Password);

    }

    public void onLogin(View view) {
        String usernmae=ed_email.getText().toString().trim();
        String password=ed_password.getText().toString().trim();

        if(TextUtils.isEmpty(usernmae)){
            Toast.makeText(getApplicationContext(),"Enter email address",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Enter Your Password",Toast.LENGTH_SHORT).show();
            return;
        }

        LoginRequest userRequest = new LoginRequest(usernmae, password);


        Call<JWTLoginSucessReponse> call = authService.login(userRequest);

        call.enqueue(new Callback<JWTLoginSucessReponse>() {
            @Override
            public void onResponse(Call<JWTLoginSucessReponse> call, Response<JWTLoginSucessReponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_SHORT).show();
                    return;
                }

                JWTLoginSucessReponse jwtLoginSucessReponse = response.body();

                token = jwtLoginSucessReponse.getToken();
                user = jwtLoginSucessReponse.getUser();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<JWTLoginSucessReponse> call, Throwable t) {
            }
        });
    }

    public void onSignup(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
