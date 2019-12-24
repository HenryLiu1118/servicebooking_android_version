package com.example.servicebookingandroid.Auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.servicebookingandroid.DashBoard.MainActivity;
import com.example.servicebookingandroid.Model.APIRequests.SignUpRequest;
import com.example.servicebookingandroid.Model.Language;
import com.example.servicebookingandroid.Model.Role;
import com.example.servicebookingandroid.Model.UserDto;
import com.example.servicebookingandroid.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AuthBaseActivity {

    private EditText ed_username,ed_password, ed_firstname, ed_lastname, ed_streetname, ed_city, ed_state, ed_zipcode, ed_phone;
    Spinner sp_role, sp_language;
    LinearLayout linearLayout;
    public View ftView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
    }

    public void initView() {
        ed_username=(EditText)findViewById(R.id.email);
        ed_password=(EditText)findViewById(R.id.password);
        ed_firstname=(EditText)findViewById(R.id.firstname);
        ed_lastname=(EditText)findViewById(R.id.lastname);
        ed_streetname=(EditText)findViewById(R.id.streetname);
        ed_city=(EditText)findViewById(R.id.city);
        ed_state=(EditText)findViewById(R.id.state);
        ed_zipcode=(EditText)findViewById(R.id.zipcode);
        ed_phone=(EditText)findViewById(R.id.phone);

        sp_role = (Spinner) findViewById(R.id.role);
        sp_language = (Spinner) findViewById(R.id.language);
        setRoleSpiner();
        setLanguageSpiner();

        linearLayout = findViewById(R.id.linearLayout);
        final LayoutInflater li=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ftView=li.inflate(R.layout.foot_view,null);
    }

    public void setRoleSpiner() {
        List<String> roleNames = new ArrayList<>();
        roleNames.add("");
        roleNames.addAll(Baseroles);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, roleNames);
        sp_role.setAdapter(arrayAdapter);
        /*
        Call<List<Role>> call = baseService.getRoles();
        call.enqueue(new Callback<List<Role>>() {
            @Override
            public void onResponse(Call<List<Role>> call, Response<List<Role>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Role> roles = response.body();
                List<String> roleNames = new ArrayList<>();
                roleNames.add("");
                for (Role role : roles) {
                    roleNames.add(role.getName());
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, roleNames);
                sp_role.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<List<Role>> call, Throwable t) {

            }
        });
         */
    }

    public void setLanguageSpiner() {
        List<String> languageNames = new ArrayList<>();
        languageNames.add("");
        languageNames.addAll(Baselanguages);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, languageNames);
        sp_language.setAdapter(arrayAdapter);
        /*
        Call<List<Language>> call = baseService.getLanguages();
        call.enqueue(new Callback<List<Language>>() {
            @Override
            public void onResponse(Call<List<Language>> call, Response<List<Language>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Language> languages = response.body();
                List<String> languageNames = new ArrayList<>();
                languageNames.add("");
                for (Language role : languages) {
                    languageNames.add(role.getName());
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, languageNames);
                sp_language.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<List<Language>> call, Throwable t) {

            }
        });
         */
    }

    public void onSignup(View view) {
        String usernmae=ed_username.getText().toString().trim();
        String password=ed_password.getText().toString().trim();
        String firstname=ed_firstname.getText().toString().trim();
        String lastname=ed_lastname.getText().toString().trim();
        String streetname=ed_streetname.getText().toString().trim();
        String city=ed_city.getText().toString().trim();
        String state=ed_state.getText().toString().trim();
        String zipcode=ed_zipcode.getText().toString().trim();
        String phone=ed_phone.getText().toString().trim();

        String role = sp_role.getSelectedItem().toString().trim();
        String langauge = sp_language.getSelectedItem().toString().trim();

        if(TextUtils.isEmpty(role)){
            Toast.makeText(getApplicationContext(),"Select Role",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(langauge)){
            Toast.makeText(getApplicationContext(),"Select languae",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(usernmae)){
            Toast.makeText(getApplicationContext(),"Enter email address",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Enter Your Password",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(firstname)){
            Toast.makeText(getApplicationContext(),"Enter Your First Name",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(lastname)){
            Toast.makeText(getApplicationContext(),"Enter Your Last Name",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(streetname)){
            Toast.makeText(getApplicationContext(),"Enter Your Street Name",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(city)){
            Toast.makeText(getApplicationContext(),"Enter Your City",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(state)){
            Toast.makeText(getApplicationContext(),"Enter Your State",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(zipcode)){
            Toast.makeText(getApplicationContext(),"Enter Your Zip Code",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(phone)){
            Toast.makeText(getApplicationContext(),"Enter Your Phone",Toast.LENGTH_SHORT).show();
            return;
        }

        linearLayout.addView(ftView);
        SignUpRequest signUpRequest = new SignUpRequest(usernmae, password, firstname, lastname, streetname, city, state, zipcode, phone, role, langauge);

        Call<String> call = authService.register(signUpRequest);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                linearLayout.removeView(ftView);
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"This email has already registered",Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void onLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
