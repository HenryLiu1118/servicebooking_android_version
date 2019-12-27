package com.example.servicebookingandroid.Model.APIRequests

data class SignUpRequest (var username: String, var password: String, var firstname: String, var lastname: String,
                          var streetname: String, var city: String, var state: String,
                          var zipcode: String, var phone: String, var role: String, var language: String) {

}