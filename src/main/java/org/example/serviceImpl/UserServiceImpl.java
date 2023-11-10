package org.example.serviceImpl;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public Integer saveUser(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(String.valueOf(id)).get();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
