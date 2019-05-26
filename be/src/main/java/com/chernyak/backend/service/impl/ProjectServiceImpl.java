package com.chernyak.backend.service.impl;

import com.chernyak.backend.entity.Project;
import com.chernyak.backend.entity.User;
import com.chernyak.backend.repository.ProjectRepository;
import com.chernyak.backend.repository.UserRepository;
import com.chernyak.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private UserRepository userRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Page<Project> getAllProjects(int page, int count, String sort, String order) {
        Sort.Direction direction = Sort.Direction.fromString(order);
        Pageable pageRequest = PageRequest.of(page, count, Sort.by(direction, sort));
        return projectRepository.findAll(pageRequest);
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long id) {
        List<User> user = userRepository.getAllByProjectId(id);

        projectRepository.deleteById(id);
    }
}
