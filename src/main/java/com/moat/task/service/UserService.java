package com.moat.task.service;

import com.moat.task.model.User;

import java.util.List;

public interface UserService {
    User create(User user);
    List<User> findAll();
    User findByUsername(String username);
}
