package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    // private long id;
    private String username;
    private String content;

    @JsonProperty("comments")
    private List<CommentDto> commentsDtos;
}
