package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User getById(Long userId);
    User getByUsername(String username);
}
