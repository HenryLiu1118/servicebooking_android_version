package com.example.servicebookingandroid.DashBoard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.servicebookingandroid.Admin.LanguageListActivity;
import com.example.servicebookingandroid.Admin.RoleListActivity;
import com.example.servicebookingandroid.Admin.ServiceTypeActivity;
import com.example.servicebookingandroid.Admin.UserListActivity;
import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Model.UserDto;
import com.example.servicebookingandroid.R;
import com.example.servicebookingandroid.Request.RequestBaseActivity;
import com.example.servicebookingandroid.Request.RequestMainActivity;
import com.example.servicebookingandroid.ServiceProvide.ServiceBaseActivity;
import com.example.servicebookingandroid.ServiceProvide.ServiceDetailActivity;
import com.example.servicebookingandroid.ServiceProvide.ServiceMainActivity;

public class MainActivity extends DashBoardBaseActivity {

    TextView tv_name, tv_email, tv_phone, tv_location;
    Button bt_requests, bt_service, bt_users, bt_serviceType, bt_roles, bt_languages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void initView() {
        tv_name = findViewById(R.id.tv_name);
        tv_email = findViewById(R.id.tv_email);
        tv_phone = findViewById(R.id.tv_phone);
        tv_location = findViewById(R.id.tv_location);

        bt_requests = findViewById(R.id.bt_requests);
        bt_service = findViewById(R.id.bt_services);

        bt_users = findViewById(R.id.btn_users);
        bt_serviceType = findViewById(R.id.btn_servicetypes);
        bt_roles = findViewById(R.id.btn_roles);
        bt_languages = findViewById(R.id.btn_languages);

        if (AuthBaseActivity.user == null) return;

        UserDto user = AuthBaseActivity.user;
        if (user.getRole().equals("Admin")) {
            bt_requests.setVisibility(View.GONE);
            bt_service.setVisibility(View.GONE);
        } else {
            bt_languages.setVisibility(View.GONE);
            bt_serviceType.setVisibility(View.GONE);
            bt_roles.setVisibility(View.GONE);
            bt_users.setVisibility(View.GONE);
            if (user.getRole().equals("Service")) {
                bt_requests.setText("All Requests");
                bt_service.setText("My Service");
            } else {
                bt_requests.setText("My Requests");
                bt_service.setText("All Services");
            }
        }
        setProfile(user);
    }

    public void setProfile(UserDto user) {
        tv_name.setText("Name: " + user.getFirstname() + " " + user.getLastname());
        tv_email.setText("Email: " + user.getUsername());
        tv_phone.setText("Phone Number: " + user.getPhone());
        tv_location.setText("Location: " + user.getStreetname() + ", " + user.getCity() + ", " + user.getState() + ", " + user.getZipcode());
    }

    public void onRequests(View view) {
        startActivity(new Intent(MainActivity.this, RequestMainActivity.class));
    }

    public void onService(View view) {
        String role = AuthBaseActivity.user.getRole();
        if (role.equals("Customer")) {
            startActivity(new Intent(this, ServiceMainActivity.class));
        } else if (role.equals("Service")) {
            startActivity(new Intent(this, ServiceDetailActivity.class));
        }
    }

    public void onRoles(View view) {
        startActivity(new Intent(MainActivity.this, RoleListActivity.class));
    }

    public void onLanguages(View view) {
        startActivity(new Intent(MainActivity.this, LanguageListActivity.class));
    }

    public void onServiceTypes(View view) {
        startActivity(new Intent(MainActivity.this, ServiceTypeActivity.class));
    }

    public void onUsers(View view) {
        startActivity(new Intent(MainActivity.this, UserListActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case R.id.Logout:
                logout();
                break;
            case R.id.UpdateAccount:
                EditProfile();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void EditProfile() {
        startActivity(new Intent(MainActivity.this, EditProfileActivity.class));
    }

    public void logout() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setMessage("Logout?")
                .setCancelable(false)
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                })
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //authService
                        AuthBaseActivity.clearAuthData();
                        //RequestService
                        RequestBaseActivity.clearRequestData();
                        //ServiceProvideService
                        ServiceBaseActivity.clearServiceData();
                        //Base
                        clearBaseData();
                        CheckUserToken();
                    }
                });
        AlertDialog A=alert.create();
        A.show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        initView();
    }
}
