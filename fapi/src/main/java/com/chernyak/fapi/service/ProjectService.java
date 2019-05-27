package com.chernyak.fapi.service;

import com.chernyak.fapi.models.Project;

import java.util.List;

public interface ProjectService {
    Project getProjectById(Long id);
    Project getProjectByCode(String code);
    Object getAllProjects(int page, int size, String sort, String order);
    Project saveProject(Project project);
    Project updateProject(Project project);
    void deleteProject(Long id);
}
