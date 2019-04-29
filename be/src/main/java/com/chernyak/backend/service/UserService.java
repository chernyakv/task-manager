package com.chernyak.backend.service;

import com.chernyak.backend.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    Page<User> getPage(int page, int count, String sort);
    User findById(Long id);
    User findByUsername(String username);
    User save(User user);
    void delete(Long id);
    List<User> getAll();
    List<User> getByProjectId(Long id);
    User update(User user);
    List<User> getAllWithoutProject();
}
