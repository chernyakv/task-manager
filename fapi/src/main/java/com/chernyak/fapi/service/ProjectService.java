package com.chernyak.fapi.service;

import com.chernyak.fapi.models.Project;

import java.util.List;

public interface ProjectService {
    Project save(Project project);
    List<Project> getAll();
}
