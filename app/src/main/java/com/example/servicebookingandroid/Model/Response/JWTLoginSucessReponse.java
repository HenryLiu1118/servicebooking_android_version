package com.example.servicebookingandroid.Model.Response;

import com.example.servicebookingandroid.Model.UserDto;

public class JWTLoginSucessReponse {
    private String token;
    private UserDto user;

    public  JWTLoginSucessReponse(){}
    public JWTLoginSucessReponse(String token, UserDto user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
