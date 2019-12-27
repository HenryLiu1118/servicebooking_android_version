package com.example.servicebookingandroid.Auth

import android.content.Intent
import com.example.servicebookingandroid.BaseActivity
import com.example.servicebookingandroid.DashBoard.MainActivity
import com.example.servicebookingandroid.Model.UserDto
import com.example.servicebookingandroid.Services.AuthService

var authService = BaseActivity.retrofit.create(AuthService::class.java)
var token: String? = null
var user: UserDto? = null

fun clearAuthData() {
    token = null
    user = null
}

open class AuthBaseActivity2 : BaseActivity() {
    override fun onStart() {
        super.onStart()
        initDada()
        if (isDataSetted && user != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}