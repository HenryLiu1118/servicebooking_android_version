package com.example.servicebookingandroid.Services;

import com.example.servicebookingandroid.Model.APIRequests.ServiceProvideRequest;
import com.example.servicebookingandroid.Model.Response.ServicesResponse;
import com.example.servicebookingandroid.Model.ServiceDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceProvideService {

    @POST("/api/provider/update")
    Call<ServiceDto> updateService(@Header("Authorization") String Authorization, @Body ServiceProvideRequest serviceProvideRequest);

    @GET("/api/provider/me")
    Call<ServiceDto> getMyService(@Header("Authorization") String Authorization);

    @GET("/api/provider")
    Call<ServicesResponse> getServices(@Query("page") int page, @Query("limit") int limit, @Header("Authorization") String Authorization);

    @GET("/api/provider/name/{serviceName}")
    Call<ServicesResponse> getServicesByName(@Path("serviceName") String serviceName, @Query("page") int page, @Query("limit") int limit, @Header("Authorization") String Authorization);

    @GET("/api/provider/language/{languageName}")
    Call<ServicesResponse> getServiceByLanguage(@Path("languageName") String languageName,
                                                @Query("page") int page,
                                                @Query("limit") int limit, @Header("Authorization") String Authorization);

    @GET("/api/provider/{serviceName}/{languageName}")
    Call<ServicesResponse> getServiceByNameAndService(@Path("serviceName") String serviceName, @Path("languageName") String languageName,
                                                      @Query("page") int page,
                                                      @Query("limit") int limit, @Header("Authorization") String Authorization);

}
