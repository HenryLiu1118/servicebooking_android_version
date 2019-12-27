package com.example.servicebookingandroid.Request

import android.app.Application
import com.example.servicebookingandroid.BaseActivity
import com.example.servicebookingandroid.Model.CommentDto
import com.example.servicebookingandroid.Model.RequestDto
import com.example.servicebookingandroid.Services.CommentService
import com.example.servicebookingandroid.Services.RequestService
import java.util.ArrayList

class RequestsData :Application() {
    var requestService = BaseActivity.retrofit.create(RequestService::class.java)
    var commentService = BaseActivity.retrofit.create(CommentService::class.java)
    var requestDtoList: ArrayList<RequestDto> = ArrayList<RequestDto>()
    var commentDtos: ArrayList<CommentDto> = ArrayList<CommentDto>()
    var selectedLanguage = "All"
    var selectedServiceType = "All"
}