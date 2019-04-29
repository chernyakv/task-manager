package com.chernyak.backend.service;

import com.chernyak.backend.entity.Task;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {
    List<Task> getTaskByAsigneeId(Long id);

    Task getById(Long id);

    List<Task> getTaskByAsigneeUsername(String username);

    List<Task> getTasksByProjectId(String projectId);

    Task save(Task task);

    Task update(Task task);

    void delete(Long id);

    Page<Task> getTasksByProjectId1(Long id, int page, int count, String sort);
}
