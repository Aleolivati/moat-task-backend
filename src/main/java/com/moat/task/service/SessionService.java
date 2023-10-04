package com.moat.task.service;

import com.moat.task.controller.dto.ResponseLoginDto;

public interface SessionService {
    ResponseLoginDto login(String username, String password);
    void logout(String token);
    void tokenValidation(String token);
}
