package com.example.servicebookingandroid.Request.Comment

import android.os.Bundle
import android.widget.Toast
import com.example.servicebookingandroid.Auth.AuthBaseActivity
import com.example.servicebookingandroid.Model.CommentDto
import com.example.servicebookingandroid.R
import com.example.servicebookingandroid.Request.RequestBaseActivity2
import com.example.servicebookingandroid.Request.commentDtos
import com.example.servicebookingandroid.Request.commentService
import kotlinx.android.synthetic.main.activity_comments.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity : RequestBaseActivity2() {

    lateinit var commentAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        initView()
    }

    fun initView() {
        commentAdapter = CommentAdapter(this, R.layout.list_view_comment_item, commentDtos)
        listView.setAdapter(commentAdapter)

        var requestId: String = intent.getStringExtra("requestId")
        var token: String = AuthBaseActivity.token

        val call = commentService.getCommentsByRequestId(requestId, token)

        call.enqueue(object: Callback<MutableList<CommentDto>> {
            override fun onResponse(call: Call<MutableList<CommentDto>>, response: Response<MutableList<CommentDto>>) {
                if (!response.isSuccessful) {
                    Toast.makeText(applicationContext, response.message(), Toast.LENGTH_SHORT).show()
                    return
                }

                commentDtos.clear()
                commentDtos.addAll(response.body()!!)
                commentAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<MutableList<CommentDto>>, t: Throwable) {

            }

        })
    }

    override fun onStart() {
        super.onStart()
    }
}
