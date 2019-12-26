package com.example.servicebookingandroid.Model.Response;

import com.example.servicebookingandroid.Model.RequestDto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RequestsResponse {
    private List<RequestDto> requestDtoList;
    private int size;

    public RequestsResponse(List<RequestDto> requestDtoList, int size) {
        this.requestDtoList = requestDtoList;
        this.size = size;
    }

    public List<RequestDto> getRequestDtoList() {
        return requestDtoList;
    }

    public void setRequestDtoList(List<RequestDto> requestDtoList) {
        this.requestDtoList = requestDtoList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
