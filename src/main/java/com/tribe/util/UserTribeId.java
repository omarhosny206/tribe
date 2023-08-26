package com.tribe.util;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserTribeId implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "tribe_id")
    private Long tribeId;
}
