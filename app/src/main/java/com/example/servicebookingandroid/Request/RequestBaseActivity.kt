package com.example.servicebookingandroid.Request

import com.example.servicebookingandroid.BaseActivity
import com.example.servicebookingandroid.Model.CommentDto
import com.example.servicebookingandroid.Model.RequestDto
import com.example.servicebookingandroid.Services.CommentService
import com.example.servicebookingandroid.Services.RequestService
import java.util.*

var requestService = BaseActivity.retrofit.create(RequestService::class.java)
var commentService = BaseActivity.retrofit.create(CommentService::class.java)
var requestDtoList: ArrayList<RequestDto> = ArrayList<RequestDto>()
var commentDtos: ArrayList<CommentDto> = ArrayList<CommentDto>()
var selectedLanguage = "All"
var selectedServiceType = "All"

fun globalclearRequestData() {
    requestDtoList =ArrayList<RequestDto>()
    commentDtos = ArrayList<CommentDto>()
    selectedLanguage = "All"
    selectedServiceType = "All"
}

open class RequestBaseActivity2: BaseActivity() {
    fun clearRequestData() {
        requestDtoList =ArrayList<RequestDto>()
        commentDtos = ArrayList<CommentDto>()
        selectedLanguage = "All"
        selectedServiceType = "All"
    }

    override fun onStart() {
        super.onStart()
        CheckUserToken ()
        checkDataSetted()
    }
}