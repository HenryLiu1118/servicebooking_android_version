package com.example.servicebookingandroid.Model

data class ServiceDto (var serviceId: Long, var detail: String, var price: String, var servicetype: String, var userDto: UserDto) {
}