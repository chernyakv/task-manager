package com.chernyak.backend.service;

import com.chernyak.backend.entity.Project;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {

    Page<Project> getPage(int page, int count, String sort);

    Project save(Project project);

    void delete(Long id);

    Project getById(Long id);

    List<Project> getAll();


}
