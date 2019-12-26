package com.example.servicebookingandroid.ServiceProvide;

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

    public static void clearServiceData() {
        serviceDtos = new ArrayList<>();
        myService = null;
        selectedLanguage = "All";
        selectedServiceType = "All";
    }

    @Override
    protected void onStart() {
        super.onStart();
        CheckUserToken();
        checkDataSetted();
    }
}
