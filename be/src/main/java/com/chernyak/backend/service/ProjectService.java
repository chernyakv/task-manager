package com.chernyak.backend.service;

import com.chernyak.backend.entity.Project;

import java.util.List;

public interface ProjectService {

    Project save(Project project);

    List<Project> getAll();

    Project delete(Project project);
}
