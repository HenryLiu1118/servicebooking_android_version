package com.example.servicebookingandroid.ServiceProvide;

import android.content.Intent;

import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Auth.LoginActivity;
import com.example.servicebookingandroid.BaseActivity;
import com.example.servicebookingandroid.Model.ServiceDto;
import com.example.servicebookingandroid.Services.ServiceProvideService;

import java.util.ArrayList;
import java.util.List;

public class ServiceBaseActivity extends BaseActivity {
    public static ServiceProvideService serviceProvideService = retrofit.create(ServiceProvideService.class);
    public static List<ServiceDto> serviceDtos = new ArrayList<>();
    public static ServiceDto myService = null;
    public static String selectedLanguage = "All";
    public static String selectedServiceType = "All";

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
