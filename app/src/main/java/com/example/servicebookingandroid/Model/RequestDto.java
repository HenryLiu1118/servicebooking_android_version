package com.example.servicebookingandroid.Model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NonNull
@Builder
public class RequestDto {
    private Long requestId;
    private String servicetype;
    private String info;
    private Boolean active;
    private UserDto userDto;
    private Date create_At;
    private Date update_At;
}
