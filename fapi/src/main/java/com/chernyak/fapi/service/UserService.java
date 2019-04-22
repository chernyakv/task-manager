package com.chernyak.fapi.service;

import com.chernyak.fapi.models.User;
import java.util.List;

public interface UserService {
    User save(User user);
    List<User> getAll();
    User getByUsername(String username);
    User deleteUser(Long id);
    List<User> getPage(int page, int size, String sort);
}
