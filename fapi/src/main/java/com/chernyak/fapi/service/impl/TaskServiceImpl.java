package com.chernyak.fapi.service.impl;

import com.chernyak.fapi.models.Task;
import com.chernyak.fapi.service.TaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Value("${backend.server.url}api/v1/tasks")
    private String backendServerUrl;

    @Override
    public Task save(Task task) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl, task, Task.class).getBody();
    }

    @Override
    public Task update(Task task) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(backendServerUrl, task, Task.class);
        return  null;
    }

    @Override
    public List<Task> getAllByUsername(String username) {
        RestTemplate restTemplate = new RestTemplate();
        Task[] taskResponse = restTemplate.getForObject(backendServerUrl + "/getByAssigneeUsername/" + username, Task[].class);
        return  Arrays.asList(taskResponse);
    }

    @Override
    public Object getAllByProjectId(String projectId, int page, int size, String sort) {
        RestTemplate restTemplate = new RestTemplate();
        Object taskResponse = restTemplate.getForObject(backendServerUrl + "/page/" + projectId + "?" + "page=" + page + "&size=" + size + "&sort=" + sort, Object.class);
        return  taskResponse;
    }

    @Override
    public Task getById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        Task task =  restTemplate.getForObject(backendServerUrl + "/" + id, Task.class);
        return task;
    }
}
