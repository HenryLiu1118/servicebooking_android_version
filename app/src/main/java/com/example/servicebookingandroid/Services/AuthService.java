package com.example.servicebookingandroid.Services;


import com.example.servicebookingandroid.Model.APIRequests.LoginRequest;
import com.example.servicebookingandroid.Model.APIRequests.SignUpRequest;
import com.example.servicebookingandroid.Model.APIRequests.UserInfoUpdateRequest;
import com.example.servicebookingandroid.Model.Language;
import com.example.servicebookingandroid.Model.Response.JWTLoginSucessReponse;
import com.example.servicebookingandroid.Model.Role;
import com.example.servicebookingandroid.Model.ServiceType;
import com.example.servicebookingandroid.Model.UserDto;

import java.util.List;

import retrofit2.*;
import retrofit2.http.*;

public interface AuthService {

    @POST("/api/users/login")
    Call<JWTLoginSucessReponse> login(@Body LoginRequest userRequest);

    @POST("/api/users/register")
    Call<String> register(@Body SignUpRequest signUpRequest);

    @PUT("/api/userinfo")
    Call<UserDto> updateUserInfo(@Header("Authorization") String Authorization, @Body UserInfoUpdateRequest userInfoUpdateRequest);

    @GET("/api/userinfo/me")
    Call<UserDto> getCurrentUserInfo();

    @GET("/api/users/role")
    Call<List<Role>> getRoles();

    @GET("/api/users/serviceType")
    Call<List<ServiceType>> getServiceTypes();

    @GET("/api/users/language")
    Call<List<Language>> getLanguages();
}
