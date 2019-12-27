package com.example.servicebookingandroid.Services

import com.example.servicebookingandroid.Model.Language
import com.example.servicebookingandroid.Model.Role
import com.example.servicebookingandroid.Model.ServiceType
import retrofit2.Call
import retrofit2.http.GET

interface BaseService {
    @GET("/api/users/role")
    fun getRoles(): Call<List<Role>>

    @GET("/api/users/serviceType")
    fun getServiceTypes(): Call<List<ServiceType>>

    @GET("/api/users/language")
    fun getLanguages(): Call<List<Language>>
}