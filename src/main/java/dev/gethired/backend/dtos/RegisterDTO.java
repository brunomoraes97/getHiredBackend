package dev.gethired.backend.dtos;

import dev.gethired.backend.models.domain.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
