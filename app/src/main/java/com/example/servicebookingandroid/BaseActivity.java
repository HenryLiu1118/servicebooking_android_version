package com.example.servicebookingandroid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Auth.LoginActivity;
import com.example.servicebookingandroid.Model.Language;
import com.example.servicebookingandroid.Model.Role;
import com.example.servicebookingandroid.Model.ServiceType;
import com.example.servicebookingandroid.Services.BaseService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseActivity extends AppCompatActivity {
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://servicebookingbackend-env.c9mdrevktd.us-east-2.elasticbeanstalk.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static BaseService baseService = retrofit.create(BaseService.class);

    public static List<String> Baselanguages = new ArrayList<>();
    public static List<String> Baseroles = new ArrayList();
    public static List<String> BaseserviceTypes = new ArrayList<>();

    public static boolean isLanguages = false;
    public static boolean isRoles = false;
    public static boolean isServiceType = false;

    public boolean isDataSetted() {
        return isLanguages && isRoles && isServiceType;
    }

    public void checkDataSetted() {
        if (!isDataSetted()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    public void clearBaseData() {
        Baselanguages = new ArrayList<>();
        Baseroles = new ArrayList();
        BaseserviceTypes = new ArrayList<>();
    }

    public void initDada() {
        initLanguage();
        initRole();
        initServiceType();
    }

    public void initLanguage() {
        Call<List<Language>> call = baseService.getLanguages();
        call.enqueue(new Callback<List<Language>>() {
            @Override
            public void onResponse(Call<List<Language>> call, Response<List<Language>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Language> languages = response.body();
                Baselanguages.clear();
                isLanguages = true;

                for (Language language : languages) {
                    Baselanguages.add(language.getName());
                }
                Log.i("henrylau1118", "Language Size: " + Baselanguages.size());
            }

            @Override
            public void onFailure(Call<List<Language>> call, Throwable t) {

            }
        });
    }

    public void initRole() {
        Call<List<Role>> call = baseService.getRoles();
        call.enqueue(new Callback<List<Role>>() {
            @Override
            public void onResponse(Call<List<Role>> call, Response<List<Role>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Role> roles = response.body();
                Baseroles.clear();
                isRoles = true;

                for (Role role : roles) {
                    Baseroles.add(role.getName());
                }
                Log.i("henrylau1118", "Role Size: " + Baseroles.size());
            }

            @Override
            public void onFailure(Call<List<Role>> call, Throwable t) {

            }
        });
    }

    public void initServiceType() {
        Call<List<ServiceType>> call = baseService.getServiceTypes();
        call.enqueue(new Callback<List<ServiceType>>() {
            @Override
            public void onResponse(Call<List<ServiceType>> call, Response<List<ServiceType>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
                    return;
                }

                List<ServiceType> serviceTypes = response.body();
                BaseserviceTypes.clear();
                isServiceType = true;

                for (ServiceType serviceType : serviceTypes) {
                    BaseserviceTypes.add(serviceType.getName());
                }
                Log.i("henrylau1118", "ServiceType Size: " + BaseserviceTypes.size());
            }

            @Override
            public void onFailure(Call<List<ServiceType>> call, Throwable t) {

            }
        });
    }

    protected boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    protected void offLine() {
        if (!isOnline()) {
            Toast.makeText(this, "Please Check your Internet Connection", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void CheckUserToken() {
        if (AuthBaseActivity.user == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        offLine();
    }
}
