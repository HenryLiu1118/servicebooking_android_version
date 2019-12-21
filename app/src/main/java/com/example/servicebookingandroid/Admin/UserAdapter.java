package com.example.servicebookingandroid.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.servicebookingandroid.Model.UserDto;
import com.example.servicebookingandroid.R;

import java.util.List;

import lombok.NonNull;

public class UserAdapter extends ArrayAdapter<UserDto> {
    private List<UserDto> userDtoList;
    private Context context;

    public UserAdapter(@NonNull Context context, int resource, @NonNull List<UserDto> objects) {
        super(context, resource, objects);
        this.userDtoList = objects;
        this.context=context;
    }

    @Override
    public int getCount() {
        return userDtoList.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.list_view_user_item, parent,false);

        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_email = view.findViewById(R.id.tv_email);
        TextView tv_phone = view.findViewById(R.id.tv_phone);
        TextView tv_address = view.findViewById(R.id.tv_address);
        TextView tv_language = view.findViewById(R.id.tv_language);
        TextView tv_role = view.findViewById(R.id.tv_role);

        UserDto userDto = userDtoList.get(position);

        tv_name.setText(userDto.getFirstname() + " " + userDto.getLastname());
        tv_email.setText("UserName: " + userDto.getUsername());
        tv_phone.setText("Phone: " + userDto.getPhone());
        tv_address.setText("Address: " + userDto.getStreetname() + ", " + userDto.getCity() + ", " + userDto.getState() + ", " + userDto.getZipcode());
        tv_role.setText("Role: " + userDto.getRole());
        tv_language.setText("Language: " + userDto.getLastname());

        return view;
    }
}
