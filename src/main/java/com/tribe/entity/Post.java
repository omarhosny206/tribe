package com.tribe.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`post`")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private Long votes = 0L;

    @Column(nullable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date date = new Date();

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Tribe tribe;

    public Post(String content, User user, Tribe tribe) {
        this.content = content;
        this.user = user;
        this.tribe = tribe;
    }
}
