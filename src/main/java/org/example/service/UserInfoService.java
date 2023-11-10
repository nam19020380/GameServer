package org.example.service;

import org.example.entity.UserInfo;
import org.springframework.http.ResponseEntity;

public interface UserInfoService {
    public ResponseEntity<?> findById(Integer id);

    public ResponseEntity<?> findByUserId(Integer userid);

    public ResponseEntity<?> createUserInfo(UserInfo userInfo);

    public ResponseEntity<?> updateUserInfo(UserInfo userInfo);

    public ResponseEntity<?> deleteUserInfo(Integer id);

}
