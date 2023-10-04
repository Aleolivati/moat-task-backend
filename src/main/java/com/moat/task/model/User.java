package com.moat.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moat.task.service.Hasher;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "registered_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullname;
    private String username;
    private String userPassword;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @JsonIgnore
    private UUID salt;

    @PreUpdate
    @PrePersist
    public void preCreate() {
        this.salt = UUID.randomUUID();
        this.userPassword = new Hasher().hash(this.userPassword, this.salt);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UUID getSalt() {
        return salt;
    }

    public void setSalt(UUID salt) {
        this.salt = salt;
    }
}
