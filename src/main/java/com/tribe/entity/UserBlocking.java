package com.tribe.entity;

import com.tribe.util.UserBlockingId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`user_blockings`")
public class UserBlocking {
    @Id
    private UserBlockingId id;

    @ManyToOne
    @MapsId("blockerId")
    @JoinColumn(name = "blocker_id")
    private User blocker;

    @ManyToOne
    @MapsId("blockedId")
    @JoinColumn(name = "blocked_id")
    private User blocked;
}
