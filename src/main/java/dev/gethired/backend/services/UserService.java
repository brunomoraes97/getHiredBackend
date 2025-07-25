package dev.gethired.backend.services;

import dev.gethired.backend.models.domain.user.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User findByEmail(String emailAddress);
    User addUser(User newUser);
    User updateUserById(Long id, User updatedUser);
    void deleteUserById(Long id);
}
