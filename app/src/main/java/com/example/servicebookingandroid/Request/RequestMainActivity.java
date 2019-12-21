package com.example.servicebookingandroid.Request;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Model.Language;
import com.example.servicebookingandroid.Model.ServiceType;
import com.example.servicebookingandroid.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestMainActivity extends RequestBaseActivity {

    Button bt_requests, bt_postRequest;
    Spinner sp_serviceType, sp_language;
    TextView tv_language, tv_serviceType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_main);
        initView();
    }

    public void initView() {
        bt_postRequest = findViewById(R.id.bt_postRequest);
        bt_requests = findViewById(R.id.bt_requests);

        sp_serviceType = findViewById(R.id.sp_serviceType);
        sp_language = findViewById(R.id.sp_langauge);
        tv_serviceType = findViewById(R.id.tv_serviceType);
        tv_language = findViewById(R.id.tv_language);

        if (AuthBaseActivity.user.getRole().equals("Customer")) {
            sp_language.setVisibility(View.INVISIBLE);
            sp_serviceType.setVisibility(View.INVISIBLE);
            tv_language.setVisibility(View.INVISIBLE);
            tv_serviceType.setVisibility(View.INVISIBLE);
        }

        if (AuthBaseActivity.user.getRole().equals("Service")) {
            bt_postRequest.setVisibility(View.INVISIBLE);
            setServiceTypeSpiner();
            setLanguageSpiner();
        }
    }

    public void onRequests(View view) {
        if (AuthBaseActivity.user.getRole().equals("Service")) {
            selectedServiceType = sp_serviceType.getSelectedItem().toString();
            selectedLanguage = sp_language.getSelectedItem().toString();
        }

        startActivity(new Intent(this, RequestListActivity.class));
    }

    public void onPostRequest(View view) {
        startActivity(new Intent(this, RequestFormActivity.class));
    }

    public void  setServiceTypeSpiner() {
        Call<List<ServiceType>> call = AuthBaseActivity.authService.getServiceTypes();

        call.enqueue(new Callback<List<ServiceType>>() {
            @Override
            public void onResponse(Call<List<ServiceType>> call, Response<List<ServiceType>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
                    return;
                }

                List<ServiceType> serviceTypes = response.body();
                List<String> ServiceTypeNames = new ArrayList<>();
                ServiceTypeNames.add("All");
                for (ServiceType serviceType : serviceTypes) {
                    ServiceTypeNames.add(serviceType.getName());
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, ServiceTypeNames);
                sp_serviceType.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<List<ServiceType>> call, Throwable t) {

            }
        });
    }

    public void setLanguageSpiner() {
        Call<List<Language>> call = AuthBaseActivity.authService.getLanguages();
        call.enqueue(new Callback<List<Language>>() {
            @Override
            public void onResponse(Call<List<Language>> call, Response<List<Language>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Language> languages = response.body();
                List<String> languageNames = new ArrayList<>();
                languageNames.add("All");
                for (Language role : languages) {
                    languageNames.add(role.getName());
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, languageNames);
                sp_language.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<List<Language>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
