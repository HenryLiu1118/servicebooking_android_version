package com.example.servicebookingandroid.Request

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.example.servicebookingandroid.Model.RequestDto
import com.example.servicebookingandroid.R

public class RequestsAdapter(context: Context, resource: Int, objects: List<RequestDto>): ArrayAdapter<RequestDto>(context, resource, objects) {
    private var requestDtos: List<RequestDto>

    init {
        requestDtos = objects
    }

    override fun getCount(): Int {
        return requestDtos.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view: View = layoutInflater.inflate(R.layout.list_view_request_item, parent, false)

        val tv_serviceType = view.findViewById<TextView>(R.id.tv_serviceType)
        val tv_info = view.findViewById<TextView>(R.id.tv_info)
        val tv_language = view.findViewById<TextView>(R.id.tv_language)
        val viewDetail = view.findViewById<Button>(R.id.viewDetail)

        viewDetail.setOnClickListener {
            val i = Intent(context, RequestDetailActivity::class.java)
            i.putExtra("index", position)
            context.startActivity(i)
        }

        val requestDto = requestDtos[position]
        tv_serviceType.text = requestDto.servicetype
        tv_info.text = requestDto.info
        tv_language.text = "Language: " + requestDto.userDto.language

        return view
    }
}