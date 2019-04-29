package com.chernyak.fapi.service;

import com.chernyak.fapi.models.User;
import java.util.List;

public interface UserService {
    User save(User user);
    List<User> getAll();
    List<User> getByProjectId(Long id);
    User getByUsername(String username);
    User deleteUser(Long id);
    List<User> getPage(int page, int size, String sort);
}
