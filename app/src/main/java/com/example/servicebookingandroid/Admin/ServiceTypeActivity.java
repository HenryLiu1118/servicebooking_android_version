package com.example.servicebookingandroid.Admin;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Model.APIRequests.AdminRequest;
import com.example.servicebookingandroid.Model.ServiceType;
import com.example.servicebookingandroid.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceTypeActivity extends AdminBaseActivity {

    ListView listView;
    EditText editText;
    List<String> serviceTypeNames;
    ArrayAdapter<String> arrayAdapter;
    public View ftView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_type);

        initView();
    }

    public void initView() {
        listView=findViewById(R.id.LV);
        editText = findViewById(R.id.ed_text);
        serviceTypeNames = new ArrayList<>();

        arrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item, serviceTypeNames);
        listView.setAdapter(arrayAdapter);
        final LayoutInflater li=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ftView=li.inflate(R.layout.foot_view,null);
        listView.addFooterView(ftView);

        Call<List<ServiceType>> call = adminService.getServiceTypes(AuthBaseActivity.token);

        call.enqueue(new Callback<List<ServiceType>>() {
            @Override
            public void onResponse(Call<List<ServiceType>> call, Response<List<ServiceType>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_SHORT).show();
                    return;
                }

                serviceTypes = response.body();

                serviceTypeNames.clear();
                for (ServiceType serviceType : serviceTypes) {
                    serviceTypeNames.add(serviceType.getName());
                }

                listView.removeFooterView(ftView);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ServiceType>> call, Throwable t) {

            }
        });
    }

    public void onAddServiceType(View view) {
        listView.addFooterView(ftView);
        String newServiceType = editText.getText().toString();
        if(TextUtils.isEmpty(newServiceType)){
            Toast.makeText(getApplicationContext(),"Enter Service Type",Toast.LENGTH_SHORT).show();
            return;
        }

        Call<ServiceType> call = adminService.saveServiceType(AuthBaseActivity.token, new AdminRequest(newServiceType));

        call.enqueue(new Callback<ServiceType>() {
            @Override
            public void onResponse(Call<ServiceType> call, Response<ServiceType> response) {
                listView.removeFooterView(ftView);
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Duplicated Service Type",Toast.LENGTH_SHORT).show();
                    editText.setText("");
                    return;
                }

                ServiceType serviceType = response.body();
                serviceTypeNames.add(serviceType.getName());
                arrayAdapter.notifyDataSetChanged();
                editText.setText("");
            }

            @Override
            public void onFailure(Call<ServiceType> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
