package com.example.servicebookingandroid.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceDto {
    private Long serviceId;
    private String detail;
    private String price;
    private String servicetype;
    private UserDto userDto;
}
