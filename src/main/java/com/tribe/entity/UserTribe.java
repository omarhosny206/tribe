package com.tribe.entity;

import com.tribe.util.UserTribeId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`user_tribes`")
public class UserTribe {
    @Id
    private UserTribeId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("tribeId")
    @JoinColumn(name = "tribe_id")
    private Tribe tribe;
}
