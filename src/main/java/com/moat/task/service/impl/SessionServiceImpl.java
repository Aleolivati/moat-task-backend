package com.moat.task.service.impl;

import com.moat.task.controller.dto.ResponseLoginDto;
import com.moat.task.exception.*;
import com.moat.task.model.Session;
import com.moat.task.model.User;
import com.moat.task.repository.SessionRepository;
import com.moat.task.service.Hasher;
import com.moat.task.service.SessionService;
import com.moat.task.service.UserService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

@Service
public class SessionServiceImpl implements SessionService {
    private final UserService userService;
    private final SessionRepository sessionRepository;
    public SessionServiceImpl(UserService userService, SessionRepository sessionRepository) {
        this.userService = userService;
        this.sessionRepository = sessionRepository; }


    @Override
    public ResponseLoginDto login(String username, String password) {
        User user = getUserByUsername(username);

        hasActiveSession(user.getId());

        validatePassword(user, password);

        Session session = createSession(user);

        return new ResponseLoginDto(user.getUsername(), user.getUserRole(), encodeToken(session.getToken()));
    }

    @Override
    public void logout(String token) {
        Session session = sessionRepository.findSessionByToken(decodeToken(token));
        if (session.getExpiresAt().isAfter(Instant.now())) {
            session.setExpiresAt(Instant.now());
            sessionRepository.save(session);
        }
    }

    @Override
    public void tokenValidation(String token) {
        tokenIsNotNull(token);

        SessionIsActive(token);
    }

    private User getUserByUsername(String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return user;
    }

    private void hasActiveSession(Integer userId) {
        Session session = sessionRepository.findSessionByUserIdAndExpiresAtAfter(userId, Instant.now());
        if (session != null) {
            throw new UserAlreadyLoggedInException("User is already logged in");
        }
    }

    private void validatePassword(User user, String password) {
        String hash = new Hasher().hash(password, user.getSalt());
        if (!Objects.equals(hash, user.getUserPassword())) {
            throw new UserPasswordIncorrectException("Password is incorrect");
        }
    }

    private Session createSession(User user) {
        Session session = new Session();
        session.setUserId(user.getId());
        session.setToken(UUID.randomUUID());
        session.setExpiresAt(Instant.now().plus(30, ChronoUnit.MINUTES));
        return sessionRepository.save(session);
    }

    private UUID decodeToken(String token) {
        byte[] decodedToken = Base64.getDecoder().decode(token);
        return UUID.fromString(new String(decodedToken));
    }

    private String encodeToken(UUID token) {
        return Base64.getEncoder().encodeToString(token.toString().getBytes());
    }

    private void tokenIsNotNull(String token) {
        if (token == null) {
            throw new TokenNotProvidedException("Access denied");
        }
    }

    private void SessionIsActive(String token) {
        Session session = sessionRepository.findSessionByToken(decodeToken(token));
        if (session == null) {
            throw new TokenNotFoundException("Access denied");
        }
        if (session.getExpiresAt().isBefore(Instant.now())) {
            throw new TokenIsExpiredException("Access denied");
        }
    }
}
