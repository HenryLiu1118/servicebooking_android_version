package com.example.servicebookingandroid.Admin;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Model.APIRequests.AdminRequest;
import com.example.servicebookingandroid.Model.Language;
import com.example.servicebookingandroid.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LanguageListActivity extends AdminBaseActivity {

    ListView listView;
    EditText editText;
    List<String> languageNames;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_list);

        initView();
    }

    public void initView() {
        listView=findViewById(R.id.LV);
        editText = findViewById(R.id.ed_text);
        languageNames = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item, languageNames);

        Call<List<Language>> call = adminService.getLanguages(AuthBaseActivity.token);

        call.enqueue(new Callback<List<Language>>() {
            @Override
            public void onResponse(Call<List<Language>> call, Response<List<Language>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_SHORT).show();
                    return;
                }

                languages = response.body();

                languageNames.clear();
                for (Language language : languages) {
                    languageNames.add(language.getName());
                }

                arrayAdapter.notifyDataSetChanged();
                //listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<List<Language>> call, Throwable t) {

            }
        });

        listView.setAdapter(arrayAdapter);
    }

    public void onAddLanguage(View view) {
        String newLangauge = editText.getText().toString();
        if(TextUtils.isEmpty(newLangauge)){
            Toast.makeText(getApplicationContext(),"Enter Language",Toast.LENGTH_SHORT).show();
            return;
        }

        Call<Language> call = adminService.saveLanguage(AuthBaseActivity.token, new AdminRequest(newLangauge));

        call.enqueue(new Callback<Language>() {
            @Override
            public void onResponse(Call<Language> call, Response<Language> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Duplicated Language",Toast.LENGTH_SHORT).show();
                    editText.setText("");
                    return;
                }

                Language language = response.body();
                languageNames.add(language.getName());
                arrayAdapter.notifyDataSetChanged();
                editText.setText("");
            }

            @Override
            public void onFailure(Call<Language> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
