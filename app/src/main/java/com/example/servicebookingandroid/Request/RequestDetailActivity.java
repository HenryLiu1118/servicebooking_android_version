package com.example.servicebookingandroid.Request;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Model.RequestDto;
import com.example.servicebookingandroid.Model.UserDto;
import com.example.servicebookingandroid.R;
import com.example.servicebookingandroid.Request.Comment.CommentFormActivity;
import com.example.servicebookingandroid.Request.Comment.CommentsActivity;

public class RequestDetailActivity extends RequestBaseActivity {

    private int index = -1;
    TextView tv_title, tv_name, tv_date, tv_info, tv_location, tv_phone, tv_language, tv_comments;
    Button bt_edit, bt_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_detail);
    }

    public void initView() {
        tv_title = findViewById(R.id.tv_title);
        tv_name = findViewById(R.id.tv_name);
        tv_date = findViewById(R.id.tv_date);
        tv_info = findViewById(R.id.tv_info);
        tv_location = findViewById(R.id.tv_location);
        tv_phone = findViewById(R.id.tv_phone);
        tv_language = findViewById(R.id.tv_language);
        tv_comments = findViewById(R.id.tv_comments);

        bt_edit = findViewById(R.id.bt_edit);
        bt_comment = findViewById(R.id.bt_comment);

        if (AuthBaseActivity.user.getRole().equals("Service")) {
            bt_comment.setText("Post Comment");
            bt_edit.setVisibility(View.INVISIBLE);
        }
    }

    public void initValue() {
        Intent intent = getIntent();
        Integer i = intent.getIntExtra("index", 0);
        if (i != null) index = i;

        RequestDto requestDto = requestDtoList.get(index);

        tv_title.setText(requestDto.getServicetype());
        String date = "Post on: "+requestDto.getCreate_At();
        if (requestDto.getUpdate_At() != null) date += "\nUpdated on: " + requestDto.getUpdate_At();
        tv_date.setText(date);
        UserDto userDto = requestDto.getUserDto();
        tv_name.setText(userDto.getFirstname() + " " + userDto.getLastname());
        String location = userDto.getStreetname() + ", " + userDto.getCity() + ", " + userDto.getState() + ", " + userDto.getZipcode();
        tv_location.setText("Location: " + location);
        tv_phone.setText("Phone Number: " + userDto.getPhone());
        tv_language.setText("Language: " + userDto.getLanguage());
        tv_info.setText(requestDto.getInfo());
    }

    public void onEditRequest(View view) {
        Intent i = new Intent(this, RequestFormActivity.class);
        i.putExtra("index", index);
        startActivity(i);
    }

    public void onComments(View view) {
        String role = AuthBaseActivity.user.getRole();
        if (role.equals("Customer")) {
            Intent i = new Intent(this, CommentsActivity.class);
            i.putExtra("requestId", String.valueOf(requestDtoList.get(index).getRequestId()));
            startActivity(i);
        } else if (role.equals("Service")){
            Intent i = new Intent(this, CommentFormActivity.class);
            i.putExtra("requestId", String.valueOf(requestDtoList.get(index).getRequestId()));
            startActivity(i);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
        initValue();
    }
}
