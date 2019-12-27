package com.example.servicebookingandroid.Model.Response

import com.example.servicebookingandroid.Model.ServiceDto

data class ServicesResponse(var serviceDtoList: List<ServiceDto>, var size: Int){}