package com.example.servicebookingandroid.Request;

import android.content.Intent;

import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Auth.LoginActivity;
import com.example.servicebookingandroid.BaseActivity;
import com.example.servicebookingandroid.Model.CommentDto;
import com.example.servicebookingandroid.Model.RequestDto;
import com.example.servicebookingandroid.Services.CommentService;
import com.example.servicebookingandroid.Services.RequestService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RequestBaseActivity extends BaseActivity {
    public static RequestService requestService = retrofit.create(RequestService.class);
    public static CommentService commentService = retrofit.create(CommentService.class);
    public static List<RequestDto> requestDtoList = new ArrayList();
    public static List<CommentDto> commentDtos = new ArrayList();
    public static String selectedLanguage = "All";
    public static String selectedServiceType = "All";

    public static void clearRequestData() {
        requestDtoList = new ArrayList();
        commentDtos = new ArrayList();
        selectedLanguage = "All";
        selectedServiceType = "All";
    }

    public void CheckUserToken() {
        if (AuthBaseActivity.user == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        CheckUserToken();
    }
}
