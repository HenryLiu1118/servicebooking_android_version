package com.example.servicebookingandroid.DashBoard;

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

import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Model.APIRequests.UserInfoUpdateRequest;
import com.example.servicebookingandroid.Model.Language;
import com.example.servicebookingandroid.Model.UserDto;
import com.example.servicebookingandroid.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends DashBoardBaseActivity {

    private EditText ed_firstname, ed_lastname, ed_streetname, ed_city, ed_state, ed_zipcode, ed_phone;
    Spinner sp_language;
    LinearLayout linearLayout;
    public View ftView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initView();
    }

    public void initView() {
        ed_firstname= findViewById(R.id.firstname);
        ed_lastname= findViewById(R.id.lastname);
        ed_streetname= findViewById(R.id.streetname);
        ed_city = findViewById(R.id.city);
        ed_state= findViewById(R.id.state);
        ed_zipcode= findViewById(R.id.zipcode);
        ed_phone= findViewById(R.id.phone);

        sp_language = findViewById(R.id.language);

        setLanguageSpiner();
        setProfileData();

        linearLayout = findViewById(R.id.linearLayout);
        final LayoutInflater li=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ftView=li.inflate(R.layout.foot_view,null);
    }

    public void setProfileData() {
        if (AuthBaseActivity.user == null) return;
        UserDto user = AuthBaseActivity.user;

        ed_firstname.setText(user.getFirstname());
        ed_lastname.setText(user.getLastname());
        ed_streetname.setText(user.getStreetname());
        ed_city.setText(user.getCity());
        ed_state.setText(user.getState());
        ed_zipcode.setText(String.valueOf(user.getZipcode()));
        ed_phone.setText(user.getPhone());


    }

    public void setLanguageSpiner() {
        List<String> languageNames = new ArrayList<>();
        languageNames.add("");
        languageNames.addAll(Baselanguages);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, languageNames);
        sp_language.setAdapter(arrayAdapter);
        int selectedPosition = arrayAdapter.getPosition(AuthBaseActivity.user.getLanguage());
        sp_language.setSelection(selectedPosition);
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
                int selectedPosition = arrayAdapter.getPosition(AuthBaseActivity.user.getLanguage());
                sp_language.setSelection(selectedPosition);
            }

            @Override
            public void onFailure(Call<List<Language>> call, Throwable t) {

            }
        });
         */
    }

    public void onEditProfile(View view) {
        String firstname=ed_firstname.getText().toString().trim();
        String lastname=ed_lastname.getText().toString().trim();
        String streetname=ed_streetname.getText().toString().trim();
        String city=ed_city.getText().toString().trim();
        String state=ed_state.getText().toString().trim();
        String zipcode=ed_zipcode.getText().toString().trim();
        String phone=ed_phone.getText().toString().trim();
        String language = sp_language.getSelectedItem().toString().trim();

        if(TextUtils.isEmpty(language)){
            Toast.makeText(getApplicationContext(),"Select languae",Toast.LENGTH_SHORT).show();
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
        UserInfoUpdateRequest userInfoUpdateRequest = new UserInfoUpdateRequest(firstname, lastname, streetname, city, state, zipcode, phone, language);
        Call<UserDto> call = AuthBaseActivity.authService.updateUserInfo(AuthBaseActivity.token, userInfoUpdateRequest);

        call.enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                linearLayout.removeView(ftView);
                if (!response.isSuccessful()) {
                    return;
                }

                AuthBaseActivity.user = response.body();
                finish();
            }

            @Override
            public void onFailure(Call<UserDto> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
