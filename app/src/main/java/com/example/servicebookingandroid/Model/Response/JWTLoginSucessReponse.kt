package com.example.servicebookingandroid.Model.Response

import com.example.servicebookingandroid.Model.UserDto

data class JWTLoginSucessReponse (var token: String, var user:UserDto){}