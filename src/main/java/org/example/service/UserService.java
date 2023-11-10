package org.example.service;

import org.example.entity.User;

import java.util.List;

public interface UserService {
    public Integer saveUser(User user);

    public User findByEmail(String email);

    public User findById(Integer id);

    public List<User> findAll();
}
