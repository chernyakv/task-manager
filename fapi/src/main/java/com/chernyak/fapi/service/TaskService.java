package com.chernyak.fapi.service;

import com.chernyak.fapi.models.Task;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {
    Task update(Task task);
    Task getById(Long id);
    Task save(Task task);
    List<Task> getAllByUsername(String username);
    Object getAllByProjectId(String projectId, int page, int size, String sort);
}
