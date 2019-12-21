package com.example.servicebookingandroid.DashBoard;

import android.content.Intent;
import android.util.Log;

import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Auth.LoginActivity;
import com.example.servicebookingandroid.BaseActivity;

public class DashBoardBaseActivity extends BaseActivity {

    public void CheckUserToken() {
        if (AuthBaseActivity.user == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        CheckUserToken();
    }
}
