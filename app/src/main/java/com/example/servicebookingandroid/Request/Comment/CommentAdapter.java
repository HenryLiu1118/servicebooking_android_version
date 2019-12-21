package com.example.servicebookingandroid.Request.Comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.servicebookingandroid.Model.CommentDto;
import com.example.servicebookingandroid.Model.UserDto;
import com.example.servicebookingandroid.R;

import java.util.List;

public class CommentAdapter extends ArrayAdapter<CommentDto> {
    private List<CommentDto> commentDtos;
    private Context context;

    public CommentAdapter(@NonNull Context context, int resource, @NonNull List<CommentDto> objects) {
        super(context, resource, objects);
        this.commentDtos = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.list_view_comment_item, parent,false);

        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_detail = view.findViewById(R.id.tv_detail);
        TextView tv_location = view.findViewById(R.id.tv_location);
        TextView tv_phone = view.findViewById(R.id.tv_phone);
        TextView tv_language = view.findViewById(R.id.tv_language);

        CommentDto commentDto = commentDtos.get(position);
        UserDto userDto = commentDto.getUserdto();

        String name = userDto.getFirstname() + " " + userDto.getLastname();
        String detail = commentDto.getCommentDetail();
        String location = userDto.getStreetname() + ", " + userDto.getCity() + ", " + userDto.getState() + ", " + userDto.getZipcode();
        String phone = userDto.getPhone();
        String language = userDto.getLanguage();

        tv_name.setText("@" + name);
        tv_detail.setText(detail);
        tv_location.setText("Location: " + location);
        tv_phone.setText("Phone: " + phone);
        tv_language.setText("Language: " + language);

        return view;
    }
}
