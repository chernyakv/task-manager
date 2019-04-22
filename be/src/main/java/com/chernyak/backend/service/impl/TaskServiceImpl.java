package com.chernyak.backend.service.impl;

import com.chernyak.backend.entity.Task;
import com.chernyak.backend.entity.User;
import com.chernyak.backend.repository.TaskRepository;
import com.chernyak.backend.repository.UserRepository;
import com.chernyak.backend.service.TaskService;
import com.chernyak.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @Autowired
    public  TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Task> getTaskByAsigneeId(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return (List<Task>)taskRepository.getTasksByAssigneeId(user);
    }
}
