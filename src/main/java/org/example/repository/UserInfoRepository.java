package org.example.repository;

import org.example.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    UserInfo findByUserId(Integer id);
}
