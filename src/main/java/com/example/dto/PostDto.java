package com.example.dto;

import com.example.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private long id;
    private String content;
    private List<Comment> comments;
}
