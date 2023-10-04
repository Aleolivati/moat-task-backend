package com.moat.task.controller;

import com.moat.task.controller.dto.LoginDto;
import com.moat.task.controller.dto.ResponseLoginDto;
import com.moat.task.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = SessionController.PATH,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class SessionController {
    private final Logger logger = LoggerFactory.getLogger(SessionController.class);
    public static final String PATH = "/api/v1/sessions";
    private final SessionService sessionService;
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDto> login(@RequestBody LoginDto request) {
        ResponseLoginDto response = sessionService.login(request.username(), request.password());
        logger.info("User logged in with success");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader(name = "Authorization") String token) {
        sessionService.logout(token);
        logger.info("User logged off with success");
        return ResponseEntity.noContent().build();
    }
}
