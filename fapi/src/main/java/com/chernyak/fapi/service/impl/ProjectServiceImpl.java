package com.chernyak.fapi.service.impl;

import com.chernyak.fapi.models.Project;
import com.chernyak.fapi.service.ProjectService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
    public Project getProjectByCode(String code) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/code/" + code, Project.class);
    }

    @Override
    public Object getAllProjects(int page, int size, String sort, String order) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder uri = UriComponentsBuilder
                .fromHttpUrl(backendServerUrl);
        uri.queryParam("page", page);
        uri.queryParam("size", size);
        uri.queryParam("sort", sort);
        uri.queryParam("order", order);
        return  restTemplate.getForObject(uri.toUriString(), Object.class);
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
