package com.example.servicebookingandroid.Auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.servicebookingandroid.DashBoard.MainActivity
import com.example.servicebookingandroid.Model.APIRequests.LoginRequest
import com.example.servicebookingandroid.Model.Response.JWTLoginSucessReponse
import com.example.servicebookingandroid.R
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AuthBaseActivity() {

    lateinit var ftView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val li: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        ftView = li.inflate(R.layout.foot_view,null)
    }

    fun onLogin(view: View) {
        var username: String = ed_email.text.toString().trim()
        var password: String = ed_password.text.toString().trim()

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Enter email address", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter Your Password",Toast.LENGTH_SHORT).show()
            return
        }

        var userRequest: LoginRequest = LoginRequest(username, password)
        linearLayout.addView(ftView)

        var call:Call<JWTLoginSucessReponse> = authService.login(userRequest)

        call.enqueue(object: Callback<JWTLoginSucessReponse> {
            override fun onFailure(call: Call<JWTLoginSucessReponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<JWTLoginSucessReponse>, response: Response<JWTLoginSucessReponse>) {
                linearLayout.removeView(ftView)
                if (!response.isSuccessful) {
                    Toast.makeText(applicationContext, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                    return
                }

                var jwtLoginSucessReponse: JWTLoginSucessReponse = response.body()!!

                token = jwtLoginSucessReponse.token
                user = jwtLoginSucessReponse.user

                if (isDataSetted()) {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                }
            }

        })
    }

    fun onSignup(view: View) {
        startActivity(Intent(applicationContext, SignUpActivity::class.java))
    }

    override fun onStart() {
        super.onStart()
    }
}
