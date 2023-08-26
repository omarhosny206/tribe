package com.tribe.entity;

import com.tribe.util.UserFollowingId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`user_followings`")
public class UserFollowing {
    @Id
    private UserFollowingId id;

    @ManyToOne
    @MapsId("followerId")
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne
    @MapsId("followeeId")
    @JoinColumn(name = "followee_id")
    private User followee;
}
