package com.chernyak.backend.repository;

import com.chernyak.backend.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, Long > {

    User findByLogin(String username);
}
