package com.chernyak.backend.service;

import com.chernyak.backend.entity.Task;
import org.springframework.data.domain.Page;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    Optional<Task> getTaskById(Long id);
    Page<Task> getTaskByAsigneeUsername(int page, int count, String sort, String order, String username);
    Page<Task> getTasksByProjectId(int page, int count, String sort, String order, Long projectId);
    Task saveTask(Task task);
    void deleteTask(Long id);
}
