package com.example.servicebookingandroid.Admin;

import android.content.Intent;

import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Auth.LoginActivity;
import com.example.servicebookingandroid.BaseActivity;
import com.example.servicebookingandroid.Model.Language;
import com.example.servicebookingandroid.Model.Role;
import com.example.servicebookingandroid.Model.ServiceType;
import com.example.servicebookingandroid.Model.UserDto;
import com.example.servicebookingandroid.Services.AdminService;

import java.util.ArrayList;
import java.util.List;

public class AdminBaseActivity extends BaseActivity {
    public static AdminService adminService = retrofit.create(AdminService.class);

    public static List<Role> roles = new ArrayList<>();
    public static List<ServiceType> serviceTypes = new ArrayList<>();
    public static List<Language> languages = new ArrayList<>();
    public static List<UserDto> users = new ArrayList<>();

    @Override
    protected void onStart() {
        super.onStart();
        CheckUserToken();
        checkDataSetted();
    }
}
