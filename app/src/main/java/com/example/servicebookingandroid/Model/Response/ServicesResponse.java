package com.example.servicebookingandroid.Model.Response;

import com.example.servicebookingandroid.Model.ServiceDto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServicesResponse {
    private List<ServiceDto> serviceDtoList;
    private int size;
}
