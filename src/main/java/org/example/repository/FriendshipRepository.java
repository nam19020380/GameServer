package org.example.repository;

import org.example.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, String> {
    public List<Friendship> findByUserId(Integer userId);

    public List<Friendship> findByUserIdAndSide(Integer userId, Integer side);

    public List<Friendship> findByFriendIdAndSide(Integer friendId, Integer side);

    public void deleteByFriendIdAndUserId(Integer userId, Integer friendId);

    public Boolean existsByUserIdAndFriendIdAndSide(Integer userId, Integer friendId, Integer side);
    public Boolean existsByUserIdAndFriendId(Integer userId, Integer friendId);
}
