package com.chernyak.fapi.service;

import com.chernyak.fapi.models.Project;

import java.util.List;

public interface ProjectService {
    Project save(Project project);
    Project delete(Long id);
    Project getById(Long id);
    List<Project> getAll();
    List<Project> getPage(int page, int size, String sort);
}
