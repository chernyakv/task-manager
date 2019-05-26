package com.chernyak.fapi.service.impl;

import com.chernyak.fapi.models.Task;
import com.chernyak.fapi.service.TaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Value("${backend.server.url}api/v1/tasks")
    private String backendServerUrl;

    @Override
    public Task getTaskById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/" + id, Task.class);
    }

    @Override
    public Object getAllTasksByUsername(int page, int size, String sort, String order, String username) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder uri = UriComponentsBuilder
                .fromHttpUrl(backendServerUrl + "/byAssignee/" + username);
        uri.queryParam("page", page);
        uri.queryParam("size", size);
        uri.queryParam("sort", sort);
        uri.queryParam("order", order);
        return  restTemplate.getForObject(uri.toUriString(), Object.class);
    }

    @Override
    public Object getAllTasksByProjectId(int page, int size, String sort, String order, Long projectId) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder uri = UriComponentsBuilder
                .fromHttpUrl(backendServerUrl + "/byProject/" + projectId);
        uri.queryParam("page", page);
        uri.queryParam("size", size);
        uri.queryParam("sort", sort);
        uri.queryParam("order", order);
        return  restTemplate.getForObject(uri.toUriString(), Object.class);
    }

    @Override
    public Task saveTask(Task task) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl, task, Task.class).getBody();
    }

    @Override
    public Task updateTask(Task task) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(backendServerUrl, task, Task.class);
        return  null;
    }

    @Override
    public void deleteTask(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/" + id);
    }
}
