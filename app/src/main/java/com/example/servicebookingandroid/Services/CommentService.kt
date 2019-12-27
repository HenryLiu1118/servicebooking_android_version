package com.example.servicebookingandroid.Services

import com.example.servicebookingandroid.Model.APIRequests.CommentRequest
import com.example.servicebookingandroid.Model.CommentDto
import retrofit2.Call
import retrofit2.http.*

interface CommentService {

    @GET("/api/comment/get/{RequestId}}")
    fun getCommentsByRequestId(@Path("RequestId") RequestId:String, @Header("Authorization") Authorization:String): Call<List<CommentDto>>

    @GET("/api/comment/check/{RequestId}}")
    fun checkDuplicateComment(@Path("RequestId") RequestId:String, @Header("Authorization") Authorization:String): Call<CommentDto>

    @POST("/api/comment/post/{RequestId}}")
    fun createComment(@Path("RequestId") RequestId:String, @Header("Authorization") Authorization:String, @Body commentRequest: CommentRequest): Call<CommentDto>
}