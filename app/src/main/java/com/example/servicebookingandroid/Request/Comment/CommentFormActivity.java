package com.example.servicebookingandroid.Request.Comment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Model.APIRequests.CommentRequest;
import com.example.servicebookingandroid.Model.CommentDto;
import com.example.servicebookingandroid.R;
import com.example.servicebookingandroid.Request.RequestBaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentFormActivity extends RequestBaseActivity {
    TextView tv_notice, tv_detail;
    EditText ed_text;
    Button bt_submit;
    String requestId = "", token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_form);
        initView();
    }

    public void initView() {
        tv_notice = findViewById(R.id.tv_notice);
        tv_detail = findViewById(R.id.tv_detail);
        ed_text = findViewById(R.id.ed_text);
        bt_submit = findViewById(R.id.bt_submit);

        Intent i = getIntent();
        requestId = i.getStringExtra("requestId");
        token = AuthBaseActivity.token;
        Call<CommentDto> call = commentService.checkDuplicateComment(requestId, token);

        call.enqueue(new Callback<CommentDto>() {
            @Override
            public void onResponse(Call<CommentDto> call, Response<CommentDto> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
                    return;
                }

                CommentDto commentDto = response.body();
                if (commentDto == null) {
                    ed_text.setVisibility(View.VISIBLE);
                    bt_submit.setVisibility(View.VISIBLE);
                } else {
                    tv_notice.setVisibility(View.VISIBLE);
                    tv_detail.setVisibility(View.VISIBLE);
                    tv_detail.setText("Your comment: " + commentDto.getCommentDetail());
                }
            }

            @Override
            public void onFailure(Call<CommentDto> call, Throwable t) {

            }
        });
    }

    public void onSubmit(View view) {
        String detail = ed_text.getText().toString();

        if(TextUtils.isEmpty(detail)){
            Toast.makeText(getApplicationContext(),"Enter the Comment Information",Toast.LENGTH_SHORT).show();
            return;
        }

        Call<CommentDto> call = commentService.createComment(requestId, token, new CommentRequest(detail));

        call.enqueue(new Callback<CommentDto>() {
            @Override
            public void onResponse(Call<CommentDto> call, Response<CommentDto> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
                    return;
                }

                CommentDto commentDto = response.body();
                finish();
            }

            @Override
            public void onFailure(Call<CommentDto> call, Throwable t) {

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
}
