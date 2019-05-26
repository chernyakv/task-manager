package com.chernyak.fapi.service;

import com.chernyak.fapi.models.User;
import java.util.List;

public interface UserService {
    User getUserById(Long id);
    User getUserByUsername(String username);
    Object getAllUsers(int page, int count, String sort);
    Object getAllUsersByProject(int page, int count, String sort, List<String> roles, Long projectId);
    Object getAllUsersWithoutProject(int page, int count, String sort, List<String> roles);
    User saveUser(User user);
    void deleteUser(Long id);
}
