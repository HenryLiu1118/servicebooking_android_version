package com.example.servicebookingandroid.Services;

import com.example.servicebookingandroid.Model.Language;
import com.example.servicebookingandroid.Model.Role;
import com.example.servicebookingandroid.Model.ServiceType;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BaseService {
    @GET("/api/users/role")
    Call<List<Role>> getRoles();

    @GET("/api/users/serviceType")
    Call<List<ServiceType>> getServiceTypes();

    @GET("/api/users/language")
    Call<List<Language>> getLanguages();
}
