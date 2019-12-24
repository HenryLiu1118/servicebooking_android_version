package com.example.servicebookingandroid.ServiceProvide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Model.Response.RequestsResponse;
import com.example.servicebookingandroid.Model.Response.ServicesResponse;
import com.example.servicebookingandroid.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceListActivity extends ServiceBaseActivity {

    ListView listView;
    ServiceAdapter serviceAdapter;
    TextView tv_listView;
    Button bt_prev, bt_next;
    int page;
    final int limit = 3;
    int size;

    public View ftView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);
    }

    public void initView() {
        listView=findViewById(R.id.LV);
        tv_listView = findViewById(R.id.tv_listView);
        bt_prev = findViewById(R.id.bt_pre);
        bt_next = findViewById(R.id.bt_next);

        serviceAdapter = new ServiceAdapter(this, R.layout.list_view_service_item, serviceDtos);
        listView.setAdapter(serviceAdapter);
        final LayoutInflater li=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ftView=li.inflate(R.layout.foot_view,null);

        page = 0;
        size = 0;
        callAPI();
    }

    public void onPrev(View view) {
        if (page <= 0) return;
        page--;
        callAPI();
        setButton();
    }

    public void onNext(View view) {
        if (page >= (int)(Math.ceil((double)size/limit)) - 1) return;
        page++;
        callAPI();
        setButton();
    }

    public void setButton() {
        if (page <= 0) {
            bt_prev.setClickable(false);
        } else {
            bt_prev.setClickable(true);
        }

        if (page >= (int)(Math.ceil((double)size/limit)) - 1) {
            bt_next.setClickable(false);
        } else {
            bt_next.setClickable(true);
        }
    }

    public void callAPI() {
        listView.addFooterView(ftView);
        String token = AuthBaseActivity.token;

        Call<ServicesResponse> call = serviceProvideService.getServices(page, limit, token);

        if (!selectedLanguage.equals("All") && !selectedServiceType.equals("All")) {
            call = serviceProvideService.getServiceByNameAndService(selectedServiceType, selectedLanguage, page, limit, token);
        } else if (!selectedServiceType.equals("All")) {
            call = serviceProvideService.getServicesByName(selectedServiceType, page, limit, token);
        } else if (!selectedLanguage.equals("All")) {
            call = serviceProvideService.getServiceByLanguage(selectedLanguage, page, limit, token);
        }

        call.enqueue(new Callback<ServicesResponse>() {
            @Override
            public void onResponse(Call<ServicesResponse> call, Response<ServicesResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
                    return;
                }

                serviceDtos.clear();
                serviceDtos.addAll(response.body().getServiceDtoList());
                serviceAdapter.notifyDataSetChanged();
                size = response.body().getSize();
                setButton();
                tv_listView.setText("List of Services: " + size + " total");

                listView.removeFooterView(ftView);
            }

            @Override
            public void onFailure(Call<ServicesResponse> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
    }
}
