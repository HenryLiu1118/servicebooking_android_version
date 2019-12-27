package com.example.servicebookingandroid.Model

data class CommentDto(var commentId: Long, var commentDetail: String, var servicetype: String,
                      var info: String, var active: Boolean, var requestUser: UserDto, var userdto: UserDto){
}