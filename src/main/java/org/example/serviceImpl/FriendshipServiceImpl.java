package org.example.serviceImpl;

import org.example.entity.Friendship;
import org.example.payload.FriendRequest;
import org.example.payload.FriendshipResponse;
import org.example.repository.FriendshipRepository;
import org.example.security.UserDetailsImpl;
import org.example.service.FriendshipService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@Service
public class FriendshipServiceImpl implements FriendshipService {
    @Autowired
    FriendshipRepository friendshipRepository;

    @Autowired
    UserService userService;

    public void saveFriendship(Friendship friendship){
        friendshipRepository.save(friendship);
    }

    public ResponseEntity<?> sendRequest(FriendRequest friendRequest){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Friendship friendship = new Friendship();
            if(!friendshipRepository.existsByUserIdAndFriendId(userDetails.getId(), userService.findByEmail(friendRequest.getUserEmail()).getId())){
                friendship.setFriendId(userService.findByEmail(friendRequest.getUserEmail()).getId());
                friendship.setUser(userService.findById(userDetails.getId()));
                friendship.setSide(1);
                friendship.setDate(new java.sql.Date(new java.util.Date().getTime()));
                friendshipRepository.save(friendship);
                return new ResponseEntity<>("Tao request thanh cong", HttpStatus.CREATED);
            } else {
                return ResponseEntity.badRequest()
                        .body("Already sent invitation");
            }

        } catch(Exception e){
            return new ResponseEntity<>("Sever error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> acceptRequest(Integer id){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Friendship friendship = friendshipRepository.findById(String.valueOf(id)).get();
            if(friendshipRepository.existsByUserIdAndFriendIdAndSide(friendship.getUser().getId(), userDetails.getId(), 1)){
                java.util.Date date = new java.util.Date(new java.util.Date().getTime());
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                friendship.setSide(2);
                friendship.setDate(sqlDate);
                Friendship friendship1 = new Friendship(userService.findById(friendship.getFriendId())
                        , friendship.getUser().getId(), 2, sqlDate);
                friendshipRepository.save(friendship);
                friendshipRepository.save(friendship1);
                return new ResponseEntity<>("Accept thanh cong", HttpStatus.CREATED);
            } else {
                return ResponseEntity.badRequest()
                        .body("Invalid invitation");
            }
        } catch(Exception e){
            return new ResponseEntity<>("Sever error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteFriendship(Integer id){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Friendship friendship = friendshipRepository.findById(String.valueOf(id)).get();
            if(friendshipRepository.existsByUserIdAndFriendId(friendship.getUser().getId(), userDetails.getId())){
                friendshipRepository.deleteByFriendIdAndUserId(friendship.getFriendId(), friendship.getUser().getId());
                friendshipRepository.deleteByFriendIdAndUserId(friendship.getUser().getId(), friendship.getFriendId());
                return new ResponseEntity<>("Xoa ban be thanh cong", HttpStatus.OK);
            } else {
                return ResponseEntity.badRequest()
                        .body("You can't delete other's friendship");
            }
        }catch (Exception e){
            return new ResponseEntity<>("Sever error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void deleteFriendship(Integer userid, Integer friendId){
        friendshipRepository.deleteByFriendIdAndUserId(userid, friendId);
        friendshipRepository.deleteByFriendIdAndUserId(friendId, userid);
    }
    public List<Friendship> findByUserUserId(Integer userId){
        return friendshipRepository.findByUserId(userId);
    }

    public ResponseEntity<?> getAllUserRequests(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<Friendship> friendshipList = friendshipRepository.findByFriendIdAndSide(userDetails.getId(), 1);
            List<FriendshipResponse> responseList = new ArrayList<>();
            for(Friendship friendship : friendshipList){
                FriendshipResponse friendshipResponse = new FriendshipResponse();
                friendshipResponse.setId(friendship.getId());
                friendshipResponse.setFromId(friendship.getUser().getId());
                friendshipResponse.setDate(friendship.getDate());
                responseList.add(friendshipResponse);
            }
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Sever error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllUserFriends(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<Friendship> friendshipList = friendshipRepository.findByFriendIdAndSide(userDetails.getId(), 2);
            List<FriendshipResponse> responseList = new ArrayList<>();
            for(Friendship friendship : friendshipList){
                FriendshipResponse friendshipResponse = new FriendshipResponse();
                friendshipResponse.setId(friendship.getId());
                friendshipResponse.setFromId(friendship.getUser().getId());
                friendshipResponse.setDate(friendship.getDate());
                responseList.add(friendshipResponse);
            }
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Sever error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Friendship> findByUserIdAndSide(Integer userId, Integer side){
        return friendshipRepository.findByUserIdAndSide(userId, side);
    }

    public List<Friendship> findByFriendIdAndSide(Integer friendId, Integer side){
        return friendshipRepository.findByFriendIdAndSide(friendId, side);
    }

    public Friendship findByFriendshipId(Integer friendshipId){
        return friendshipRepository.findById(String.valueOf(friendshipId)).get();
    }

    public Boolean existsByUserUserIdAndFriendId(Integer userId, Integer friendId){
        return friendshipRepository.existsByUserIdAndFriendId(userId, friendId);
    }

    public Boolean existsByUserUserIdAndFriendIdAndSide(Integer userId, Integer friendId, Integer side){
        return friendshipRepository.existsByUserIdAndFriendIdAndSide(userId, friendId, side);
    }
}
