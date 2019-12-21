package com.example.servicebookingandroid.Request.Comment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.servicebookingandroid.Auth.AuthBaseActivity;
import com.example.servicebookingandroid.Model.CommentDto;
import com.example.servicebookingandroid.R;
import com.example.servicebookingandroid.Request.RequestBaseActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsActivity extends RequestBaseActivity {

    ListView listView;
    CommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        initView();
    }

    public void initView() {
        listView=findViewById(R.id.LV);
        commentAdapter = new CommentAdapter(this, R.layout.list_view_comment_item, commentDtos);
        listView.setAdapter(commentAdapter);

        Intent i = getIntent();
        String requestId = i.getStringExtra("requestId");
        String token = AuthBaseActivity.token;

        Call<List<CommentDto>> call = commentService.getCommentsByRequestId(requestId, token);

        call.enqueue(new Callback<List<CommentDto>>() {
            @Override
            public void onResponse(Call<List<CommentDto>> call, Response<List<CommentDto>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_SHORT).show();
                    return;
                }

                commentDtos.clear();;
                commentDtos.addAll(response.body());
                commentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CommentDto>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
