package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "`user`")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private long id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String username;

    private String password;
    @Column(unique = true)
    private String email;



    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @ManyToMany
    private List<User> followers;
}
