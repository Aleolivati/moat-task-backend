package com.moat.task.service.impl;

import com.moat.task.exception.UserAlreadyExistsException;
import com.moat.task.model.User;
import com.moat.task.repository.UserRepository;
import com.moat.task.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User create(User user) {
        User existingUser = userRepository.findUserByUsername(user.getUsername());
        if (existingUser != null) {
            throw new UserAlreadyExistsException("This user already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
