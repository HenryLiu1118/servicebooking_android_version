package com.example.servicebookingandroid.Request;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Model.Response.RequestsResponse;
import com.example.servicebookingandroid.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestListActivity extends RequestBaseActivity {

    ListView listView;
    RequestsAdapter requestsAdapter;
    TextView tv_listView;
    Button bt_prev, bt_next;
    int page;
    final int limit = 3;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);
    }

    public void initView() {
        listView=findViewById(R.id.LV);
        tv_listView = findViewById(R.id.tv_listView);
        bt_prev = findViewById(R.id.bt_pre);
        bt_next = findViewById(R.id.bt_next);
        requestsAdapter = new RequestsAdapter(this, R.layout.list_view_request_item, requestDtoList);
        listView.setAdapter(requestsAdapter);
        page = 0;
        size = 0;
        callAPI();
        setButton();
    }

    public void onPrev(View view) {
        page--;
        callAPI();
        setButton();
    }

    public void onNext(View view) {
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
        String role = AuthBaseActivity.user.getRole();
        String token = AuthBaseActivity.token;

        Call<RequestsResponse> call = requestService.getMyRequests(page, limit, token);

        if (role.equals("Customer")) {
            //call = requestService.getMyRequests(page, limit,token);
        } else if (role.equals("Service")){
            if (!selectedLanguage.equals("All") && !selectedServiceType.equals("All")) {
                call = requestService.getRequestByServiceTypeAndLanguage(selectedServiceType, selectedLanguage, page, limit, token);
            } else if (!selectedServiceType.equals("All")) {
                call = requestService.getRequestsByServiceType(selectedServiceType, page, limit, token);
            } else if (!selectedLanguage.equals("All")) {
                call = requestService.getRequestByLanguage(selectedLanguage, page, limit, token);
            } else {
                call = requestService.getAllRequests(page, limit, token);
            }
        }

        call.enqueue(new Callback<RequestsResponse>() {
            @Override
            public void onResponse(Call<RequestsResponse> call, Response<RequestsResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
                    return;
                }

                requestDtoList.clear();
                requestDtoList.addAll(response.body().getRequestDtoList());
                requestsAdapter.notifyDataSetChanged();
                size = response.body().getSize();
                tv_listView.setText("List of Requests: " + size + " total");
            }

            @Override
            public void onFailure(Call<RequestsResponse> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
    }
}
