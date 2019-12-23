package com.example.servicebookingandroid.ServiceProvide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Model.APIRequests.ServiceProvideRequest;
import com.example.servicebookingandroid.Model.ServiceDto;
import com.example.servicebookingandroid.Model.ServiceType;
import com.example.servicebookingandroid.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceFormActivity extends ServiceBaseActivity {

    TextView tv_title;
    Spinner sp_serviceType;
    EditText ed_text, ed_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_form);
        initView();
        initValue();
    }

    public void initView() {
        tv_title = findViewById(R.id.tv_title);
        sp_serviceType = findViewById(R.id.sp_serviceType);
        ed_text = findViewById(R.id.ed_text);
        ed_price = findViewById(R.id.ed_price);
        setServiceTypeSpiner();
    }

    public void initValue() {
        if (myService == null) return;
        ed_text.setText(myService.getDetail());
        ed_price.setText(myService.getPrice());
    }

    public void setServiceTypeSpiner() {
        List<String> ServiceTypeNames = new ArrayList<>();
        ServiceTypeNames.add("");
        ServiceTypeNames.addAll(BaseserviceTypes);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, ServiceTypeNames);
        sp_serviceType.setAdapter(arrayAdapter);
        if (myService != null){
            int selectedPosition = arrayAdapter.getPosition(myService.getServicetype());
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
                if (myService != null){
                    int selectedPosition = arrayAdapter.getPosition(myService.getServicetype());
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
        String price = ed_price.getText().toString();

        if(TextUtils.isEmpty(serviceType)){
            Toast.makeText(getApplicationContext(),"Select serviceType",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(info)){
            Toast.makeText(getApplicationContext(),"Enter The Request Information",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(price)){
            Toast.makeText(getApplicationContext(),"Enter the price",Toast.LENGTH_SHORT).show();
            return;
        }

        ServiceProvideRequest serviceProvideRequest = new ServiceProvideRequest(info, price, serviceType);
        String token = AuthBaseActivity.token;

        Call<ServiceDto> call = serviceProvideService.updateService(token, serviceProvideRequest);

        call.enqueue(new Callback<ServiceDto>() {
            @Override
            public void onResponse(Call<ServiceDto> call, Response<ServiceDto> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
                    return;
                }
                finish();
            }

            @Override
            public void onFailure(Call<ServiceDto> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
