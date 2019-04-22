package com.chernyak.backend.service.impl;

import com.chernyak.backend.entity.Project;
import com.chernyak.backend.repository.ProjectRepository;
import com.chernyak.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Project> getAll() {
        List<Project> projects = projectRepository.findAll();
        return projects;
    }

    @Override
    public Project delete(Project project) {
        return null;
    }
}
