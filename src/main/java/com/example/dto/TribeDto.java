package com.example.dto;

import com.example.entity.Post;
import com.example.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TribeDto {
    @NotEmpty
    private String name;

    private List<User> users = new ArrayList<>();

    private List<Post> posts = new ArrayList<>();
}
