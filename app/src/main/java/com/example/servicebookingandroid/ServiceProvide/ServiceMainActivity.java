package com.example.servicebookingandroid.ServiceProvide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.servicebookingandroid.R;

import java.util.ArrayList;
import java.util.List;

public class ServiceMainActivity extends ServiceBaseActivity {

    Button bt_service;
    Spinner sp_serviceType, sp_language;
    TextView tv_language, tv_serviceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_main);
    }

    public void initView() {
        bt_service = findViewById(R.id.bt_service);

        sp_serviceType = findViewById(R.id.sp_serviceType);
        sp_language = findViewById(R.id.sp_langauge);
        tv_serviceType = findViewById(R.id.tv_serviceType);
        tv_language = findViewById(R.id.tv_language);
        setServiceTypeSpiner();
        setLanguageSpiner();
    }

    public void onService(View view) {
        selectedServiceType = sp_serviceType.getSelectedItem().toString();
        selectedLanguage = sp_language.getSelectedItem().toString();
        startActivity(new Intent(this, ServiceListActivity.class));
    }

    public void  setServiceTypeSpiner() {
        List<String> ServiceTypeNames = new ArrayList<>();
        ServiceTypeNames.add("All");
        ServiceTypeNames.addAll(BaseserviceTypes);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, ServiceTypeNames);
        sp_serviceType.setAdapter(arrayAdapter);
    }

    public void setLanguageSpiner() {
        List<String> languageNames = new ArrayList<>();
        languageNames.add("All");
        languageNames.addAll(Baselanguages);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, languageNames);
        sp_language.setAdapter(arrayAdapter);
        /*
        Call<List<Language>> call = baseService.getLanguages();
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
         */
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
    }
}
