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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Page<Task> getTaskByAsigneeUsername(int page, int count, String sort, String username) {
        Pageable pageRequest = PageRequest.of(page, count, Sort.by(sort));
        Optional<User> user = userRepository.findByUsername(username);
        return taskRepository.findAllByAssigneeId(pageRequest, user.get());
    }

    @Override
    public Page<Task> getTasksByProjectId(int page, int count, String sort, Long id) {
        Pageable pageRequest = PageRequest.of(page, count, Sort.by(sort));
        return taskRepository.findAllByProjectIdId(pageRequest, id);
    }

    @Override
    public Task saveTask(Task task) {
        if(task.getTicketCode() == null) {
            Optional<Project> project = projectRepository.findById(task.getProjectId().getId());
            if(!project.isPresent()){
                throw new RuntimeException("Project not found");
            }
            task.setTicketCode(project.get().getProjectCode() + "-" + taskRepository.countByProjectId(project.get()));
        }
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
