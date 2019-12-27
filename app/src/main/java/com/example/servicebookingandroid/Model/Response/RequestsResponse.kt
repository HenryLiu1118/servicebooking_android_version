package com.example.servicebookingandroid.Model.Response

import com.example.servicebookingandroid.Model.RequestDto

data class RequestsResponse(var requestDtoList: List<RequestDto>, var size: Int) {}