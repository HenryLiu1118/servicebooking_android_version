package com.example.servicebookingandroid.DashBoard

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.servicebookingandroid.Auth.AuthBaseActivity
import com.example.servicebookingandroid.BaseActivity
import com.example.servicebookingandroid.Model.APIRequests.UserInfoUpdateRequest
import com.example.servicebookingandroid.Model.UserDto
import com.example.servicebookingandroid.R
import kotlinx.android.synthetic.main.activity_edit_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileActivity : DashBoardBaseActivity() {

    lateinit var ftView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        initView()
    }

    fun initView() {
        setLanguageSpiner()
        setProfileData()

        var li:LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        ftView = li.inflate(R.layout.foot_view,null)
    }

    fun setProfileData() {
        if (AuthBaseActivity.user == null) {
            return
        }

        var user: UserDto = AuthBaseActivity.user
        ed_firstname.setText(user.firstname)
        ed_lastname.setText(user.lastname)
        ed_streetname.setText(user.streetname)
        ed_city.setText(user.city)
        ed_state.setText(user.state)
        ed_zipcode.setText(user.zipcode.toString())
        ed_phone.setText(user.phone)
    }

    fun setLanguageSpiner() {
        var languageNames = ArrayList<String>()
        languageNames.addAll(BaseActivity.Baselanguages)
        var arrayAdapter = ArrayAdapter<String>(baseContext, android.R.layout.simple_spinner_item, languageNames)
        sp_language.setAdapter(arrayAdapter)
        var selectedPosition: Int = arrayAdapter.getPosition(AuthBaseActivity.user.language)
        sp_language.setSelection(selectedPosition)
    }

    fun onEditProfile(view: View) {
        val firstname = ed_firstname.text.toString().trim { it <= ' ' }
        val lastname = ed_lastname.text.toString().trim { it <= ' ' }
        val streetname = ed_streetname.text.toString().trim { it <= ' ' }
        val city = ed_city.text.toString().trim { it <= ' ' }
        val state = ed_state.text.toString().trim { it <= ' ' }
        val zipcode = ed_zipcode.text.toString().trim { it <= ' ' }
        val phone = ed_phone.text.toString().trim { it <= ' ' }
        val language = sp_language.selectedItem.toString().trim { it <= ' ' }

        if (TextUtils.isEmpty(language)) {
            Toast.makeText(applicationContext, "Select languae", Toast.LENGTH_SHORT).show()
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
        val userInfoUpdateRequest = UserInfoUpdateRequest(firstname, lastname, streetname, city, state, zipcode, phone, language)
        val call = AuthBaseActivity.authService.updateUserInfo(AuthBaseActivity.token, userInfoUpdateRequest)

        call.enqueue(object : Callback<UserDto> {
            override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                linearLayout.removeView(ftView)
                if (!response.isSuccessful) {
                    return
                }

                AuthBaseActivity.user = response.body()
                finish()
            }

            override fun onFailure(call: Call<UserDto>, t: Throwable) {

            }
        })
    }

    override fun onStart() {
        super.onStart()
    }
}
