package com.moat.task.repository;

import com.moat.task.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
    Session findSessionByToken(UUID token);
    Session findSessionByUserId(Integer id);
    Session findSessionByUserIdAndExpiresAtAfter(Integer id, Instant now);

}
