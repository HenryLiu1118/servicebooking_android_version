package com.example.servicebookingandroid.ServiceProvide;

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
import com.example.servicebookingandroid.Model.ServiceDto;
import com.example.servicebookingandroid.R;

import java.util.List;

public class ServiceAdapter extends ArrayAdapter<ServiceDto> {
    private List<ServiceDto> serviceDtos;
    private Context context;


    public ServiceAdapter(@NonNull Context context, int resource, @NonNull List<ServiceDto> objects) {
        super(context, resource, objects);
        this.serviceDtos = objects;
        this.context = context;
    }

    @Override
    public int getCount() {
        return serviceDtos.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.list_view_service_item, parent,false);

        TextView tv_serviceType = view.findViewById(R.id.tv_serviceType);
        TextView tv_price = view.findViewById(R.id.tv_price);
        TextView tv_language = view.findViewById(R.id.tv_language);
        Button viewDetail = view.findViewById(R.id.viewDetail);

        viewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ServiceDetailActivity.class);
                i.putExtra("index", position);
                context.startActivity(i);
            }
        });

        ServiceDto serviceDto = serviceDtos.get(position);
        tv_serviceType.setText(serviceDto.getServicetype());
        tv_price.setText("Price: " + serviceDto.getPrice());
        tv_language.setText("Language: " + serviceDto.getUserDto().getLanguage());

        return view;
    }
}
