package com.chernyak.backend.repository;

import com.chernyak.backend.entity.Project;
import com.chernyak.backend.entity.Role;
import com.chernyak.backend.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User, Long >{
    Optional<User> findByUsername(String username);
    Page<User> findAllByRolesIn(Pageable pageable, List<Role> roles);
    Page<User> findAllByProjectIdAndRolesIn(Pageable pageable, Long id, List<Role> roles);
    Page<User> findAllByProjectIsNull(Pageable pageable);
    Page<Object> findAllByProjectIsNull(Specification spec,Pageable pageable);

    @Query("select u.lastName, t.title from User u inner join Task t on t.assigneeId=u.id")
    List<Object>  findTest(String name);

    List<Object> findAll(Specification spec);

}
