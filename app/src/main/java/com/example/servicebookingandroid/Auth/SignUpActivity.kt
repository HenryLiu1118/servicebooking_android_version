package com.example.servicebookingandroid.Auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.servicebookingandroid.BaseActivity
import com.example.servicebookingandroid.Model.APIRequests.SignUpRequest
import com.example.servicebookingandroid.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AuthBaseActivity() {

    lateinit var ftView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initView()
    }

    fun initView() {
        setRoleSpiner()
        setLanguageSpiner()
        var li: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        ftView = li.inflate(R.layout.foot_view, null)
    }

    fun setRoleSpiner() {
        var roleNames = ArrayList<String>()
        roleNames.add("")
        roleNames.addAll(BaseActivity.Baseroles)
        var arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(baseContext,  android.R.layout.simple_spinner_item, roleNames)
        sp_role.setAdapter(arrayAdapter)
    }

    fun setLanguageSpiner() {
        var languageNames = ArrayList<String>()
        languageNames.add("")
        languageNames.addAll(BaseActivity.Baselanguages)
        var arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(baseContext,  android.R.layout.simple_spinner_item, languageNames)
        sp_language.setAdapter(arrayAdapter)
    }

    fun onSignup(view: View) {
        val usernmae = ed_username.text.toString().trim { it <= ' ' }
        val password = ed_password.text.toString().trim { it <= ' ' }
        val firstname = ed_firstname.text.toString().trim { it <= ' ' }
        val lastname = ed_lastname.text.toString().trim { it <= ' ' }
        val streetname = ed_streetname.text.toString().trim { it <= ' ' }
        val city = ed_city.text.toString().trim { it <= ' ' }
        val state = ed_state.text.toString().trim { it <= ' ' }
        val zipcode = ed_zipcode.text.toString().trim { it <= ' ' }
        val phone = ed_phone.text.toString().trim { it <= ' ' }

        val role = sp_role.selectedItem.toString().trim { it <= ' ' }
        val langauge = sp_language.selectedItem.toString().trim { it <= ' ' }

        if (TextUtils.isEmpty(role)) {
            Toast.makeText(applicationContext, "Select Role", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(langauge)) {
            Toast.makeText(applicationContext, "Select languae", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(usernmae)) {
            Toast.makeText(applicationContext, "Enter email address", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "Enter Your Password", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(firstname)) {
            Toast.makeText(applicationContext, "Enter Your First Name", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(lastname)) {
            Toast.makeText(applicationContext, "Enter Your Last Name", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(streetname)) {
            Toast.makeText(applicationContext, "Enter Your Street Name", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(city)) {
            Toast.makeText(applicationContext, "Enter Your City", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(state)) {
            Toast.makeText(applicationContext, "Enter Your State", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(zipcode)) {
            Toast.makeText(applicationContext, "Enter Your Zip Code", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(applicationContext, "Enter Your Phone", Toast.LENGTH_SHORT).show()
            return
        }

        linearLayout.addView(ftView)
        val signUpRequest: SignUpRequest = SignUpRequest(usernmae, password, firstname, lastname, streetname, city, state, zipcode, phone, role, langauge)

        val call = authService.register(signUpRequest)

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                linearLayout.removeView(ftView)
                if (!response.isSuccessful) {
                    Toast.makeText(applicationContext, "This email has already registered", Toast.LENGTH_SHORT).show()
                    finish()
                    return
                }
                finish()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {

            }
        })
    }

    fun onLogin(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onStart() {
        super.onStart()
    }
}
