package com.example.dto;

import com.example.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class HistoryId implements Serializable {
    @NotEmpty
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
