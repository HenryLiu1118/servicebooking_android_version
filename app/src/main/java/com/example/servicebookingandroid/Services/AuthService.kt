package com.example.servicebookingandroid.Services

import com.example.servicebookingandroid.Model.APIRequests.LoginRequest
import com.example.servicebookingandroid.Model.APIRequests.SignUpRequest
import com.example.servicebookingandroid.Model.APIRequests.UserInfoUpdateRequest
import com.example.servicebookingandroid.Model.Response.JWTLoginSucessReponse
import com.example.servicebookingandroid.Model.UserDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface AuthService {

    @POST("/api/users/login")
    fun login(@Body userRequest: LoginRequest):  Call<JWTLoginSucessReponse>

    @POST("/api/users/register")
    fun register(@Body signUpRequest: SignUpRequest): Call<String>

    @PUT("/api/userinfo")
    fun updateUserInfo(@Header("Authorization") Authorization: String, @Body userInfoUpdateRequest: UserInfoUpdateRequest): Call<UserDto>
}