package org.example.service;

import org.example.entity.Friendship;
import org.example.payload.FriendRequest;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.List;

public interface FriendshipService {
    public void saveFriendship(Friendship friendship);

    public ResponseEntity<?> sendRequest(FriendRequest friendRequest);

    public ResponseEntity<?> acceptRequest(Integer id);

    public ResponseEntity<?> deleteFriendship(Integer id);

    public ResponseEntity<?> getAllUserRequests();

    public ResponseEntity<?> getAllUserFriends();

    public void deleteFriendship(Integer userid, Integer friendId);
    public List<Friendship> findByUserUserId(Integer userId);

    public List<Friendship> findByUserIdAndSide(Integer userId, Integer side);

    public List<Friendship> findByFriendIdAndSide(Integer friendId, Integer side);

    public Friendship findByFriendshipId(Integer friendshipId);

    public Boolean existsByUserUserIdAndFriendId(Integer userId, Integer friendId);

    public Boolean existsByUserUserIdAndFriendIdAndSide(Integer userId, Integer friendId, Integer side);
}
