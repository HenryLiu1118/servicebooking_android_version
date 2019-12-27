package com.example.servicebookingandroid.Request.Comment

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.example.servicebookingandroid.Auth.AuthBaseActivity
import com.example.servicebookingandroid.Model.APIRequests.CommentRequest
import com.example.servicebookingandroid.Model.CommentDto
import com.example.servicebookingandroid.R
import com.example.servicebookingandroid.Request.RequestBaseActivity2
import com.example.servicebookingandroid.Request.commentService
import kotlinx.android.synthetic.main.activity_comment_form.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentFormActivity : RequestBaseActivity2() {

    var requestId:String = ""
    var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_form)
        initView()
    }

    fun initView() {
        requestId = intent.getStringExtra("requestId")
        token = AuthBaseActivity.token

        var call = commentService.checkDuplicateComment(requestId, token)

        call.enqueue(object: Callback<CommentDto> {
            override fun onFailure(call: Call<CommentDto>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<CommentDto>, response: Response<CommentDto>) {
                if (!response.isSuccessful) {
                    Toast.makeText(applicationContext, "Server Error", Toast.LENGTH_SHORT).show()
                    return
                }

                var commentDto = response.body()

                if (commentDto == null) {
                    ed_text.setVisibility(View.VISIBLE)
                    bt_submit.setVisibility(View.VISIBLE)
                } else {
                    tv_notice.setVisibility(View.VISIBLE)
                    tv_detail.setVisibility(View.VISIBLE)
                    tv_detail.setText("Your comment: " + commentDto.commentDetail)
                }
            }
        })
    }

    fun onSubmit(view: View) {
        val detail = ed_text.text.toString()

        if (TextUtils.isEmpty(detail)) {
            Toast.makeText(applicationContext, "Enter the Comment Information", Toast.LENGTH_SHORT).show()
            return
        }

        var call = commentService.createComment(requestId, token, CommentRequest(detail))

        call.enqueue(object : Callback<CommentDto> {
            override fun onFailure(call: Call<CommentDto>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<CommentDto>, response: Response<CommentDto>) {
                if (!response.isSuccessful) {
                    Toast.makeText(applicationContext, "Server Error", Toast.LENGTH_SHORT).show()
                    return
                }
                val commentDto = response.body()
                finish()
            }
        })
    }

    override fun onStart() {
        super.onStart()
    }
}
