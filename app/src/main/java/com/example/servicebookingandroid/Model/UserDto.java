package com.example.servicebookingandroid.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long userId;

    private String username;

    private String firstname;

    private String lastname;

    private String streetname;

    private String city;

    private String state;

    private Integer zipcode;

    private String phone;

    private String language;

    private String role;


}
