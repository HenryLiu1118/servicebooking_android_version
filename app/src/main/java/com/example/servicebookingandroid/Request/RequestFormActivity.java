package com.example.servicebookingandroid.Request;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Model.APIRequests.RequestOrderRequest;
import com.example.servicebookingandroid.Model.RequestDto;
import com.example.servicebookingandroid.Model.ServiceType;
import com.example.servicebookingandroid.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestFormActivity extends RequestBaseActivity {

    private int index = -1;
    private boolean editMode = false;
    TextView tv_title;
    Spinner sp_serviceType;
    EditText ed_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_form);
        initView();
        initValue();
    }

    public void initView() {
        tv_title = findViewById(R.id.tv_title);
        sp_serviceType = findViewById(R.id.sp_serviceType);
        ed_text = findViewById(R.id.ed_text);
    }

    public void initValue() {
        Intent intent = getIntent();
        Integer i = intent.getIntExtra("index", -1);
        if (i == -1) {
            setServiceTypeSpiner();
            return;
        }
        index = i;
        editMode = true;
        tv_title.setText("Update Request");
        ed_text.setText(requestDtoList.get(index).getInfo());
        setServiceTypeSpiner();
    }

    public void setServiceTypeSpiner() {
        List<String> ServiceTypeNames = new ArrayList<>();
        ServiceTypeNames.add("");
        ServiceTypeNames.addAll(BaseserviceTypes);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, ServiceTypeNames);
        sp_serviceType.setAdapter(arrayAdapter);
        if (editMode){
            int selectedPosition = arrayAdapter.getPosition(requestDtoList.get(index).getServicetype());
            sp_serviceType.setSelection(selectedPosition);
        }

/*
        Call<List<ServiceType>> call = baseService.getServiceTypes();
        call.enqueue(new Callback<List<ServiceType>>() {
            @Override
            public void onResponse(Call<List<ServiceType>> call, Response<List<ServiceType>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
                    return;
                }

                List<ServiceType> serviceTypes = response.body();
                List<String> ServiceTypeNames = new ArrayList<>();
                ServiceTypeNames.add("");
                for (ServiceType serviceType : serviceTypes) {
                    ServiceTypeNames.add(serviceType.getName());
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, ServiceTypeNames);
                sp_serviceType.setAdapter(arrayAdapter);
                if (editMode){
                    int selectedPosition = arrayAdapter.getPosition(requestDtoList.get(index).getServicetype());
                    sp_serviceType.setSelection(selectedPosition);
                }
            }

            @Override
            public void onFailure(Call<List<ServiceType>> call, Throwable t) {

            }
        });
*/
    }

    public void onSubmit(View view) {
        String serviceType = sp_serviceType.getSelectedItem().toString();
        String info = ed_text.getText().toString();

        if(TextUtils.isEmpty(serviceType)){
            Toast.makeText(getApplicationContext(),"Select serviceType",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(info)){
            Toast.makeText(getApplicationContext(),"Enter The Request Information",Toast.LENGTH_SHORT).show();
            return;
        }

        RequestOrderRequest requestOrderRequest = new RequestOrderRequest(serviceType, info);
        String token = AuthBaseActivity.token;

        Call<RequestDto> call = requestService.createRequest(token, requestOrderRequest);

        if (editMode) {
            String id = String.valueOf(requestDtoList.get(index).getRequestId());
            call = requestService.updateRequest(id, token, requestOrderRequest);
        }

        call.enqueue(new Callback<RequestDto>() {
            @Override
            public void onResponse(Call<RequestDto> call, Response<RequestDto> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (editMode) {
                    requestDtoList.set(index, response.body());
                } else {
                    requestDtoList.add(response.body());
                }
                finish();
            }

            @Override
            public void onFailure(Call<RequestDto> call, Throwable t) { }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
