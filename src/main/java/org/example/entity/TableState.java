package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name = "TABLESTATE")
@Entity
@Getter
@Setter
public class TableState {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "PLAYER1")
    private Integer player1Id;

    @Column(name = "PLAYER2")
    private Integer player2Id;

    @Column(name = "PLAYER1_MOVE")
    private String player1IdMove;

    @Column(name = "PLAYER2_MOVE")
    private String player2IdMove;

    @Column(name = "CHATLOG")
    private List<String> chatLog;
}
