package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Table(name = "FRIENDSHIP")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Friendship {
    @Id
    @GeneratedValue
    @Column(name = "FRIENDSHIP_ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    User user;

    @Column(name = "FRIEND_ID")
    private Integer friendId;

    @Column(name = "SIDE")
    private int side;

    @Column(name = "DATE", nullable = true)
    Date date;

    public Friendship(User user, Integer friendId, int side, Date date) {
        this.user = user;
        this.friendId = friendId;
        this.side = side;
        this.date = date;
    }
}
