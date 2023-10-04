package com.moat.task.controller;

import com.moat.task.model.User;
import com.moat.task.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
    value = UserController.PATH,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    public static final String PATH = "/api/v1/users";
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User request) {
        User user = userService.create(request);
        logger.info("User created with success");
        return ResponseEntity.ok(user);
    }
}
