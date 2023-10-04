package com.moat.task.controller.dto;

import com.moat.task.model.UserRole;

public record ResponseLoginDto(String username, UserRole userRole, String token) {
}
