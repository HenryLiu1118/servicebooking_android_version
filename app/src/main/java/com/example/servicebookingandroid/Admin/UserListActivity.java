package com.example.servicebookingandroid.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Model.UserDto;
import com.example.servicebookingandroid.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListActivity extends AdminBaseActivity {

    ListView listView;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        initView();
    }

    public void initView() {
        listView=findViewById(R.id.LV);
        userAdapter = new UserAdapter(this, R.layout.list_view_user_item, users);
        listView.setAdapter(userAdapter);

        Call<List<UserDto>> call = adminService.getUsers(AuthBaseActivity.token);

        call.enqueue(new Callback<List<UserDto>>() {
            @Override
            public void onResponse(Call<List<UserDto>> call, Response<List<UserDto>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_SHORT).show();
                    return;
                }

                users.clear();
                users.addAll(response.body());
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<UserDto>> call, Throwable t) {
                Log.i("henrylau1118", call.toString());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
