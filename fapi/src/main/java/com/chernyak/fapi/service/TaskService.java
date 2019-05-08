package com.chernyak.fapi.service;

import com.chernyak.fapi.models.Task;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {
    Task getTaskById(Long id);
    Object getAllTasksByUsername(int page, int count, String sort, String username);
    Object getAllTasksByProjectId(int page, int count, String sort, Long projectId);
    Task saveTask(Task task);
    Task updateTask(Task task);
    void deleteTask(Long id);
}
