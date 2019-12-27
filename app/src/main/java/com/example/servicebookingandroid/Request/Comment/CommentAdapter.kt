package com.example.servicebookingandroid.Request.Comment

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.servicebookingandroid.Model.CommentDto
import com.example.servicebookingandroid.R

public class CommentAdapter: ArrayAdapter<CommentDto> {
    lateinit var commentDtos: List<CommentDto>
    constructor(context: Context, resource: Int, objects: List<CommentDto>):super(context, resource, objects){
        commentDtos = objects
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view: View = layoutInflater.inflate(R.layout.list_view_comment_item, parent,false)

        val tv_name = view.findViewById<TextView>(R.id.tv_name)
        val tv_detail = view.findViewById<TextView>(R.id.tv_detail)
        val tv_location = view.findViewById<TextView>(R.id.tv_location)
        val tv_phone = view.findViewById<TextView>(R.id.tv_phone)
        val tv_language = view.findViewById<TextView>(R.id.tv_language)

        val commentDto = commentDtos[position]
        var userDto = commentDto.userdto

        val name = userDto.firstname + " " + userDto.lastname
        val detail = commentDto.commentDetail
        val location = userDto.streetname + ", " + userDto.city + ", " + userDto.state + ", " + userDto.zipcode
        val phone = userDto.phone
        val language = userDto.language

        tv_name.text = "@$name"
        tv_detail.text = detail
        tv_location.text = "Location: $location"
        tv_phone.text = "Phone: $phone"
        tv_language.text = "Language: $language"

        return view
    }
}