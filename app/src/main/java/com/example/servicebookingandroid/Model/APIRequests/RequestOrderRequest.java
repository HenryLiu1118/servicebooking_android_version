package com.example.servicebookingandroid.Model.APIRequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RequestOrderRequest {
    private String servicetype;
    private String info;

    public RequestOrderRequest(String servicetype, String info) {
        this.servicetype = servicetype;
        this.info = info;
    }
}
