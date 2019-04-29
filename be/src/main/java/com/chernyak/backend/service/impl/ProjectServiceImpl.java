package com.chernyak.backend.service.impl;

import com.chernyak.backend.entity.Project;
import com.chernyak.backend.repository.ProjectRepository;
import com.chernyak.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {


    ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project save(Project project) {
        Project savedProject = projectRepository.save(project);

        return savedProject;
    }

    @Override
    public Page<Project> getPage(int page, int count, String sort) {
        Pageable pageRequest = PageRequest.of(page, count);
        return projectRepository.findAll(pageRequest);
    }

    @Override
    public List<Project> getAll() {
        List<Project> projects = projectRepository.findAll();
        return projects;
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project getById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }
}
