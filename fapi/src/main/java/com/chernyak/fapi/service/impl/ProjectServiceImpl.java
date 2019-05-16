package com.chernyak.fapi.service.impl;

import com.chernyak.fapi.models.Project;
import com.chernyak.fapi.service.ProjectService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Value("${backend.server.url}api/v1/projects")
    private String backendServerUrl;

    @Override
    public Project getProjectById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/" + id, Project.class);
    }

    @Override
    public Object getAllProjects(int page, int size, String sort) {
        RestTemplate restTemplate = new RestTemplate();
        Object projectResponse = restTemplate.getForObject(backendServerUrl + "?page=" + page + "&size=" + size + "&sort=" + sort, Object.class);
        return  projectResponse;
    }

    @Override
    public Project saveProject(Project project) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl, project, Project.class).getBody();
    }

    @Override
    public Project updateProject(Project project) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(backendServerUrl, project, Project.class);
        return  null;
    }

    @Override
    public void deleteProject(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/" + id);
    }
}
