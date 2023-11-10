package org.example.repository;

import org.example.entity.TableState;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableStateRepository extends JpaRepository<TableState, String> {
    List<TableState> findByType(String type);

    TableState findByPlayer1IdOrPlayer2Id(Integer player1Id, Integer player2Id);
}
