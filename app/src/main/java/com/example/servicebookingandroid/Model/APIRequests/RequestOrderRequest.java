package com.example.servicebookingandroid.Model.APIRequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestOrderRequest {
    private String servicetype;
    private String info;
}
