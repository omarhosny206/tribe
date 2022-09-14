package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private String username;

    @NotEmpty
    private String content;

    private Long votes = 0L;

    private Date date = new Date();

    @JsonProperty("comments")
    private List<CommentDto> commentsDtos;
}
