package com.example.servicebookingandroid.Model

data class UserDto(var userId: Long, var username: String, var firstname: String,
                   var lastname: String, var streetname: String, var city: String,
                   var state: String, var zipcode: Int, var phone: String, var language: String, var role: String) {


}