package com.example.servicebookingandroid.Services;

import com.example.servicebookingandroid.Model.APIRequests.CommentRequest;
import com.example.servicebookingandroid.Model.CommentDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentService {

    @GET("/api/comment/get/{RequestId}}")
    Call<List<CommentDto>> getCommentsByRequestId(@Path("RequestId") String RequestId, @Header("Authorization") String Authorization);

    @GET("/api/comment/check/{RequestId}}")
    Call<CommentDto> checkDuplicateComment(@Path("RequestId") String RequestId, @Header("Authorization") String Authorization);

    @POST("/api/comment/post/{RequestId}}")
    Call<CommentDto> createComment(@Path("RequestId") String RequestId, @Header("Authorization") String Authorization, @Body CommentRequest commentRequest);
}
