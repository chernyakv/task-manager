package com.chernyak.backend.service;

import com.chernyak.backend.entity.Project;
import com.chernyak.backend.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(Long id);
    Optional<User> getUserByUsername(String username);
    Page<User> getAllUsers(int page, int count, String sort);
    Page<User> getAllUsersByProjectAndRolesIn(int page, int count, String sort, List<String> roles, Long projectId);
    Page<User> getAllUsersWithoutProject(int page, int count, String sort);
    User saveUser(User user);
    void deleteUser(Long id);
}
