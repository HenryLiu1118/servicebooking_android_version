package com.example.servicebookingandroid.ServiceProvide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Model.ServiceDto;
import com.example.servicebookingandroid.Model.UserDto;
import com.example.servicebookingandroid.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceDetailActivity extends ServiceBaseActivity {

    private int index = -1;
    TextView tv_title, tv_name, tv_price, tv_info, tv_location, tv_phone, tv_language;
    Button bt_edit;
    ServiceDto serviceDto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);
    }

    public void initView() {
        tv_title = findViewById(R.id.tv_title);
        tv_name = findViewById(R.id.tv_name);
        tv_price = findViewById(R.id.tv_price);
        tv_info = findViewById(R.id.tv_info);
        tv_location = findViewById(R.id.tv_location);
        tv_phone = findViewById(R.id.tv_phone);
        tv_language = findViewById(R.id.tv_language);

        bt_edit = findViewById(R.id.bt_edit);

        if (AuthBaseActivity.user.getRole().equals("Customer")) {
            bt_edit.setVisibility(View.INVISIBLE);
        }
    }

    public void initServiceData() {
        Intent intent = getIntent();
        Integer i = intent.getIntExtra("index", -1);


        if (i != -1) {
            index = i;
            serviceDto = serviceDtos.get(index);
            initValue();
        } else {
            Call<ServiceDto> call = serviceProvideService.getMyService(AuthBaseActivity.token);
            call.enqueue(new Callback<ServiceDto>() {
                @Override
                public void onResponse(Call<ServiceDto> call, Response<ServiceDto> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    myService = response.body();
                    serviceDto = myService;
                    if (myService != null) {
                        initValue();
                    } else {
                        remindService();
                    }
                }

                @Override
                public void onFailure(Call<ServiceDto> call, Throwable t) {

                }
            });
        }
    }

    public void initValue() {
        tv_title.setText(serviceDto.getServicetype());
        UserDto userDto = serviceDto.getUserDto();
        tv_name.setText(userDto.getFirstname() + " " + userDto.getLastname());
        tv_price.setText("Price: " + serviceDto.getPrice());
        tv_info.setText(serviceDto.getDetail());
        String location = userDto.getStreetname() + ", " + userDto.getCity() + ", " + userDto.getState() + ", " + userDto.getZipcode();
        tv_location.setText("Location: " + location);
        tv_phone.setText("Phone Number: " + userDto.getPhone());
        tv_language.setText("Language: " + userDto.getLanguage());
    }

    public void remindService() {
        tv_title.setVisibility(View.GONE);
        tv_name.setVisibility(View.GONE);
        tv_price.setVisibility(View.GONE);
        tv_location.setVisibility(View.GONE);
        tv_language.setVisibility(View.GONE);
        tv_phone.setVisibility(View.GONE);
        tv_info.setText("Please fill your Service Information");
    }

    public void onEditService(View view) {
        startActivity(new Intent(this, ServiceFormActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
        initServiceData();
    }
}
