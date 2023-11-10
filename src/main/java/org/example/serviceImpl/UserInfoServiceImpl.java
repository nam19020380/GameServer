package org.example.serviceImpl;

import org.example.entity.UserInfo;
import org.example.repository.UserInfoRepository;
import org.example.security.UserDetailsImpl;
import org.example.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public ResponseEntity<?> findById(Integer id) {
        try{
            Optional<UserInfo> userInfo = userInfoRepository.findById(String.valueOf(id));
            if(userInfo.isPresent()){
                return ResponseEntity.ok().body(userInfo.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch(Exception e){
            return ResponseEntity.internalServerError().body("Sever Error");
        }
    }

    @Override
    public ResponseEntity<?> findByUserId(Integer userid) {
        try{
            UserInfo userInfo = userInfoRepository.findByUserId(userid);
            if(userInfo != null){
                return ResponseEntity.ok().body(userInfo);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch(Exception e){
            return ResponseEntity.internalServerError().body("Sever Error");
        }
    }

    @Override
    public ResponseEntity<?> createUserInfo(UserInfo userInfo) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            if(Objects.equals(userInfo.getUserId(), userDetails.getId())){
                UserInfo userInfo1 = userInfoRepository.save(userInfo);
                return new ResponseEntity<>(userInfo1, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Ban khong co quyen!", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Sever Error");
        }
    }

    @Override
    public ResponseEntity<?> updateUserInfo(UserInfo userInfo) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            if(Objects.equals(userDetails.getId(), userInfo.getId())){
                UserInfo userInfo1 = userInfoRepository.save(userInfo);
                return new ResponseEntity<>(userInfo1, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Ban khong co quyen!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Sever Error");
        }
    }

    @Override
    public ResponseEntity<?> deleteUserInfo(Integer id) {
        try{
            userInfoRepository.deleteById(String.valueOf(id));
            return new ResponseEntity<>("Xoa thanh cong", HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Sever Error");
        }
    }
}
