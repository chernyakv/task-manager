package com.chernyak.backend.repository;

import com.chernyak.backend.entity.Project;
import com.chernyak.backend.entity.Task;
import com.chernyak.backend.entity.User;
import org.omg.IOP.TAG_ALTERNATE_IIOP_ADDRESS;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findAllByAssigneeId(Pageable pageable, User user);
    Page<Task> findAllByProjectIdId(Pageable pageable, Long id);
}
