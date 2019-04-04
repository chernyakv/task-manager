package com.chernyak.backend.repository;

import com.chernyak.backend.entity.Task;
import com.chernyak.backend.entity.User;
import org.omg.IOP.TAG_ALTERNATE_IIOP_ADDRESS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Iterable<Task> getTasksByAssigneeId(Long id);

}
