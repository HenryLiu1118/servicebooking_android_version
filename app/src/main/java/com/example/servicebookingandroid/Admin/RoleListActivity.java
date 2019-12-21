package com.example.servicebookingandroid.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Auth.LoginActivity;
import com.example.servicebookingandroid.Model.APIRequests.AdminRequest;
import com.example.servicebookingandroid.Model.Language;
import com.example.servicebookingandroid.Model.Role;
import com.example.servicebookingandroid.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoleListActivity extends AdminBaseActivity {

    ListView listView;
    EditText editText;
    List<String> roleNames;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_list);
        initView();
    }

    public void initView() {
        listView=findViewById(R.id.LV);
        editText = findViewById(R.id.ed_text);
        roleNames = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item, roleNames);

        Call<List<Role>> call = adminService.getRoles(AuthBaseActivity.token);

        call.enqueue(new Callback<List<Role>>() {
            @Override
            public void onResponse(Call<List<Role>> call, Response<List<Role>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_SHORT).show();
                    return;
                }

                roles = response.body();

                roleNames.clear();
                for (Role role : roles) {
                    roleNames.add(role.getName());
                }

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Role>> call, Throwable t) {

            }
        });

        listView.setAdapter(arrayAdapter);
    }

    public void onAddRole(View view) {
        String newRole = editText.getText().toString();
        if(TextUtils.isEmpty(newRole)){
            Toast.makeText(getApplicationContext(),"Enter Role",Toast.LENGTH_SHORT).show();
            return;
        }

        Call<Role> call = adminService.saveRole(AuthBaseActivity.token, new AdminRequest(newRole));

        call.enqueue(new Callback<Role>() {
            @Override
            public void onResponse(Call<Role> call, Response<Role> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Duplicated Role",Toast.LENGTH_SHORT).show();
                    editText.setText("");
                    return;
                }

                Role role = response.body();
                roleNames.add(role.getName());
                arrayAdapter.notifyDataSetChanged();
                editText.setText("");
            }

            @Override
            public void onFailure(Call<Role> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
