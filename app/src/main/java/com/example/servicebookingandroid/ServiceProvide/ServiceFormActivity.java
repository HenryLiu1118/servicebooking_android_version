package com.example.servicebookingandroid.ServiceProvide;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Model.APIRequests.ServiceProvideRequest;
import com.example.servicebookingandroid.Model.ServiceDto;
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

    LinearLayout linearLayout;
    public View ftView;

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

        linearLayout = findViewById(R.id.linearLayout);
        final LayoutInflater li=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ftView=li.inflate(R.layout.foot_view,null);

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
        linearLayout.addView(ftView);

        Call<ServiceDto> call = serviceProvideService.updateService(token, serviceProvideRequest);

        call.enqueue(new Callback<ServiceDto>() {
            @Override
            public void onResponse(Call<ServiceDto> call, Response<ServiceDto> response) {
                linearLayout.removeView(ftView);
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
