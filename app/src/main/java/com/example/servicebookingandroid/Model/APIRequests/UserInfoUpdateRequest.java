package com.example.servicebookingandroid.Model.APIRequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Data
public class UserInfoUpdateRequest {
    private String firstname;
    private String lastname;
    private String streetname;
    private String city;
    private String state;
    private String zipcode;
    private String phone;
    private String language;

    public UserInfoUpdateRequest(String firstname, String lastname, String streetname, String city, String state, String zipcode, String phone, String language) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.streetname = streetname;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.phone = phone;
        this.language = language;
    }
}
