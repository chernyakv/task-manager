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
    public Project save(Project project) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl, project, Project.class).getBody();
    }

    @Override
    public Project delete(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/" + id);
        return null;
    }

    @Override
    public Project getById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        Project project =  restTemplate.getForObject(backendServerUrl + "/" + id, Project.class);
        return project;
    }

    @Override
    public List<Project> getPage(int page, int size, String sort) {
        RestTemplate restTemplate = new RestTemplate();
        Project[] usersResponse = restTemplate.getForObject(backendServerUrl + "/page?" + "page=" + page + "&size=" + size + "&sort=" + sort , Project[].class);
        return Arrays.asList(usersResponse);
    }

    @Override
    public List<Project> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        Project[] projectsResponse = restTemplate.getForObject(backendServerUrl, Project[].class);
        return Arrays.asList(projectsResponse);
    }


}
