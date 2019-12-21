package com.example.servicebookingandroid.Model.Response;

import com.example.servicebookingandroid.Model.RequestDto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestsResponse {
    private List<RequestDto> requestDtoList;
    private int size;
}
