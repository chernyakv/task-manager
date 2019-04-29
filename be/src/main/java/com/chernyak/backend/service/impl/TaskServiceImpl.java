package com.chernyak.backend.service.impl;

import com.chernyak.backend.entity.Project;
import com.chernyak.backend.entity.Task;
import com.chernyak.backend.entity.User;
import com.chernyak.backend.repository.ProjectRepository;
import com.chernyak.backend.repository.TaskRepository;
import com.chernyak.backend.repository.UserRepository;
import com.chernyak.backend.service.TaskService;
import com.chernyak.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private ProjectRepository projectRepository;

    @Autowired
    public  TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Task> getTaskByAsigneeId(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return (List<Task>)taskRepository.getTasksByAssigneeId(user);
    }

    @Override
    public List<Task> getTaskByAsigneeUsername(String username) {
        User user = userRepository.findByUsername(username);
        return (List<Task>)taskRepository.getTasksByAssigneeId(user);
    }

    @Override
    public List<Task> getTasksByProjectId(String projectId) {
        Project project = projectRepository.findById(Long.parseLong(projectId)).orElse(null);
        return (List<Task>)taskRepository.getTasksByProjectId(project);
    }

    @Override
    public Task save(Task task) {
        Task savedTast = taskRepository.save(task);

        return savedTast;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Task getById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public Task update(Task task) {
        Task savedTask = taskRepository.save(task);
        return savedTask;
    }

    @Override
    public Page<Task> getTasksByProjectId1(Long id, int page, int count, String sort) {
        Pageable pageRequest = PageRequest.of(page, count);
        Project project = projectRepository.findById(id).orElse(null);
        return taskRepository.findTaskByProjectId(project, pageRequest);
    }
}
