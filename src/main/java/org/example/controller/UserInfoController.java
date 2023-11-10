package org.example.controller;

import org.example.entity.UserInfo;
import org.example.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/userInfo")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;
    @GetMapping
    public ResponseEntity<?> getUserInfo(@RequestParam Integer id){
        return userInfoService.findById(id);
    }
    @GetMapping(value = "/userId")
    public ResponseEntity<?> getUserInfoByUserId(@RequestParam Integer id){
        return userInfoService.findByUserId(id);
    }
    @PostMapping
    public ResponseEntity<?> createUserInfo(@RequestBody UserInfo userInfo){
        return userInfoService.createUserInfo(userInfo);
    }

    @PutMapping
    public ResponseEntity<?> updateUserInfo(@RequestBody UserInfo userInfo){
        return userInfoService.updateUserInfo(userInfo);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserInfo(@RequestParam Integer id){
        return userInfoService.deleteUserInfo(id);
    }
}
