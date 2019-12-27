package com.example.servicebookingandroid.Model.APIRequests

data class UserInfoUpdateRequest (var firstname: String, var lastname: String,
                                  var streetname: String, var city: String, var state: String,
                                  var zipcode: String, var phone: String, var language: String) {
}