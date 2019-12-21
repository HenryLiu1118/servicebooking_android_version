package com.example.servicebookingandroid.Model.APIRequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String streetname;
    private String city;
    private String state;
    private String zipcode;
    private String phone;
    private String role;
    private String language;
}
