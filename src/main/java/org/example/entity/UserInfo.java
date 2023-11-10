package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "USERINFO")
@Entity
@Getter
@Setter
public class UserInfo {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USERID")
    private Integer userId;

    @Column(name = "WINCOUNT")
    private Integer winCount;

    @Column(name = "LOSECOUNT")
    private Integer loseCount;

    @Column(name = "ELO")
    private Integer elo;

    @Column(name = "FORGETPASSWORD")
    private  Boolean isForgetPassword;
}
