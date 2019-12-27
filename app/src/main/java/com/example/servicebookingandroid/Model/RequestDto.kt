package com.example.servicebookingandroid.Model

import java.util.*

data class RequestDto (var requestId: Long, var servicetype: String,
                       var info: String, var active: Boolean, var userDto: UserDto,
                       var create_At: Date, var update_At: Date) {

}