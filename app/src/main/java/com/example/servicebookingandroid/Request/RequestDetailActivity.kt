package com.example.servicebookingandroid.Request

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.servicebookingandroid.Auth.AuthBaseActivity
import com.example.servicebookingandroid.Model.RequestDto
import com.example.servicebookingandroid.R
import com.example.servicebookingandroid.Request.Comment.CommentFormActivity
import com.example.servicebookingandroid.Request.Comment.CommentsActivity
import kotlinx.android.synthetic.main.activity_request_detail.*

class RequestDetailActivity : RequestBaseActivity() {

    var index: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_detail)
    }

    fun initView() {
        if (AuthBaseActivity.user.role == "Service") {
            bt_comment.setText("Post Comment")
            bt_edit.setVisibility(View.INVISIBLE)
        }
    }

    fun initValue() {
        var intent: Intent = getIntent()
        var i: Int = intent.getIntExtra("index", -1)
        if (i != -1) index = i

        var requestDto: RequestDto = requestDtoList[index]

        tv_title.text = requestDto.servicetype
        var date = "Post on: " + requestDto.create_At
        if (requestDto.update_At != null) date += "\nUpdated on: " + requestDto.update_At
        tv_date.text = date
        val userDto = requestDto.userDto
        tv_name.text = userDto.firstname + " " + userDto.lastname
        val location = userDto.streetname + ", " + userDto.city + ", " + userDto.state + ", " + userDto.zipcode
        tv_location.text = "Location: $location"
        tv_phone.text = "Phone Number: " + userDto.phone
        tv_language.text = "Language: " + userDto.language
        tv_info.text = requestDto.info
    }

    fun onEditRequest(view: View) {
        val i = Intent(this, RequestFormActivity::class.java)
        i.putExtra("index", index)
        startActivity(i)
    }

    fun onComments(view: View) {
        val role = AuthBaseActivity.user.role
        if (role == "Customer") {
            val i = Intent(this, CommentsActivity::class.java)
            i.putExtra("requestId", requestDtoList[index].requestId.toString())
            startActivity(i)
        } else if (role == "Service") {
            val i = Intent(this, CommentFormActivity::class.java)
            i.putExtra("requestId", requestDtoList[index].requestId.toString())
            startActivity(i)
        }
    }

    override fun onStart() {
        super.onStart()
        initView()
        initValue()
    }
}
