package com.chernyak.backend.service.impl;

import com.chernyak.backend.entity.Task;
import com.chernyak.backend.repository.TaskRepository;
import com.chernyak.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public  TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getTaskByAsigneeId(Long id) {
        return (List<Task>)taskRepository.getTasksByAssigneeId(id);
    }
}
