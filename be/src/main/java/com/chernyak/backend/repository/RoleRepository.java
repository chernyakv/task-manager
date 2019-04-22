package com.chernyak.backend.repository;

import com.chernyak.backend.entity.Role;
import com.chernyak.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long > {
    Role findByName(String name);
}
