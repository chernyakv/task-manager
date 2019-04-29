package com.chernyak.backend.repository;

import com.chernyak.backend.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findAll(Pageable pageable);

}
