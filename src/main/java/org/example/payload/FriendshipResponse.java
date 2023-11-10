package org.example.payload;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class FriendshipResponse {
    private Integer id;
    private Integer fromId;
    private Date date;

    public FriendshipResponse() {
    }
}
