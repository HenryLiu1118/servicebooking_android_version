package com.example.servicebookingandroid.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {
    private Long commentId;
    private String commentDetail;
    private String servicetype;
    private String info;
    private Boolean active;
    private UserDto requestUser;
    private UserDto userdto;
}
