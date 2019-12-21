package com.example.servicebookingandroid.Services;


import com.example.servicebookingandroid.Model.APIRequests.RequestOrderRequest;
import com.example.servicebookingandroid.Model.RequestDto;
import com.example.servicebookingandroid.Model.Response.RequestsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestService {

    @POST("/api/request")
    Call<RequestDto> createRequest(@Header("Authorization") String Authorization, @Body RequestOrderRequest requestOrderRequest);

    @PUT("/api/request/id/{RequestId}")
    Call<RequestDto> updateRequest(@Path("RequestId") String RequestId, @Header("Authorization") String Authorization, @Body RequestOrderRequest requestOrderRequest);

    @GET("/api/request/All")
    Call<RequestsResponse> getAllRequests(@Query("page") int page, @Query("limit") int limit, @Header("Authorization") String Authorization);

    @GET("/api/request/me")
    Call<RequestsResponse> getMyRequests(@Query("page") int page, @Query("limit") int limit, @Header("Authorization") String Authorization);

    @GET("/api/request/name/{serviceName}")
    Call<RequestsResponse> getRequestsByServiceType(@Path("serviceName") String serviceName, @Query("page") int page, @Query("limit") int limit, @Header("Authorization") String Authorization);

    @GET("/api/request/language/{languageName}")
    Call<RequestsResponse> getRequestByLanguage(@Path("languageName") String languageName,
                                                @Query("page") int page,
                                                @Query("limit") int limit, @Header("Authorization") String Authorization);

    @GET("/api/request/{serviceName}/{languageName}")
    Call<RequestsResponse> getRequestByServiceTypeAndLanguage(@Path("serviceName") String serviceName, @Path("languageName") String languageName,
                                                              @Query("page") int page,
                                                              @Query("limit") int limit, @Header("Authorization") String Authorization);
}
