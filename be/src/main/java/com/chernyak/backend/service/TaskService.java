package com.chernyak.backend.service;

import com.chernyak.backend.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getTaskByAsigneeId(Long id);
}
