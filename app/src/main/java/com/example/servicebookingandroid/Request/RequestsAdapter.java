package com.example.servicebookingandroid.Request;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.servicebookingandroid.Model.RequestDto;
import com.example.servicebookingandroid.R;

import java.util.List;


public class RequestsAdapter extends ArrayAdapter<RequestDto> {
    private List<RequestDto> requestDtos;
    private Context context;

    public RequestsAdapter(@NonNull Context context, int resource, @NonNull List<RequestDto> objects) {
        super(context, resource, objects);
        this.requestDtos = objects;
        this.context = context;
    }

    @Override
    public int getCount() {
        return requestDtos.size();
    }

    @Override
    public View getView(final int position, @Nullable View convertView, @androidx.annotation.NonNull ViewGroup parent) {
        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.list_view_request_item, parent,false);

        TextView tv_serviceType = view.findViewById(R.id.tv_serviceType);
        TextView tv_info = view.findViewById(R.id.tv_info);
        TextView tv_language = view.findViewById(R.id.tv_language);
        Button viewDetail = view.findViewById(R.id.viewDetail);

        viewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, RequestDetailActivity.class);
                i.putExtra("index", position);
                context.startActivity(i);
            }
        });

        RequestDto requestDto = requestDtos.get(position);
        tv_serviceType.setText(requestDto.getServicetype());
        tv_info.setText(requestDto.getInfo());
        tv_language.setText("Language: " + requestDto.getUserDto().getLanguage());

        return view;
    }
}
