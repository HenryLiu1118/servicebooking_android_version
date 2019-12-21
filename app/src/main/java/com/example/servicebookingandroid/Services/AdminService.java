package com.example.servicebookingandroid.Services;

import com.example.servicebookingandroid.Model.APIRequests.AdminRequest;
import com.example.servicebookingandroid.Model.Language;
import com.example.servicebookingandroid.Model.Role;
import com.example.servicebookingandroid.Model.ServiceType;
import com.example.servicebookingandroid.Model.UserDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AdminService {

    @GET("/api/admin/role")
    Call<List<Role>> getRoles(@Header("Authorization") String Authorization);

    @GET("/api/admin/language")
    Call<List<Language>> getLanguages(@Header("Authorization") String Authorization);

    @GET("/api/admin/serviceType")
    Call<List<ServiceType>> getServiceTypes(@Header("Authorization") String Authorization);

    @GET("/api/admin/user")
    Call<List<UserDto>> getUsers(@Header("Authorization") String Authorization);

    @POST("/api/admin/role")
    Call<Role> saveRole(@Header("Authorization") String Authorization, @Body AdminRequest adminRequest);

    @POST("/api/admin/serviceType")
    Call<ServiceType> saveServiceType(@Header("Authorization") String Authorization, @Body AdminRequest adminRequest);

    @POST("/api/admin/language")
    Call<Language> saveLanguage(@Header("Authorization") String Authorization, @Body AdminRequest adminRequest);
}
