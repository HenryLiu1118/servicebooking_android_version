package com.example.servicebookingandroid.Model.APIRequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
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
}
