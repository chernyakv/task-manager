package com.chernyak.backend.repository;

import com.chernyak.backend.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepository extends JpaRepository<User, Long > {

    User findByUsername(String username);

    Page<User> findAll(Pageable pageable);

    List<User> findAllByProjectIsNull();

    List<User> findByProjectId(Long id);

}
