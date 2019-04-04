package com.chernyak.backend.service;

import com.chernyak.backend.entity.User;

import java.util.List;

public interface UserService {
    User getById(Long id);
    User getByUsername(String username);
    void save(User user);
    void delete(Long id);
    List<User> getAll();
}
