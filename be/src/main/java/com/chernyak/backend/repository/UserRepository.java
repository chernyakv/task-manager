package com.chernyak.backend.repository;

import com.chernyak.backend.entity.Project;
import com.chernyak.backend.entity.Role;
import com.chernyak.backend.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User, Long > {
    Optional<User> findByUsername(String username);
    Page<User> findAll(Pageable pageable);
    Page<User> findAllByProjectIdAndRolesIn(Pageable pageable, Long id, List<Role> roles);
    Page<User> findAllByProjectIsNull(Pageable pageable);
}
