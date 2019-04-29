package com.chernyak.fapi.controller;

import com.chernyak.fapi.models.Task;
import com.chernyak.fapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping(value = "")
    public ResponseEntity<?> save(@RequestBody Task task) {
        taskService.save(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getByAssigneeUsername/{username}")
    public List<Task> getAllUsers(@PathVariable String username){
        return taskService.getAllByUsername(username);
    }

    @GetMapping(value = "/getByProjectId/{projectId}")
    public Object getAllUsersByProjectId(@PathVariable String projectId,
                                         @RequestParam(value = "page") int page,
                                         @RequestParam(value = "size") int size,
                                         @RequestParam(value = "sort") String sort){
        return taskService.getAllByProjectId(projectId, page, size, sort);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id){
        Task project = taskService.getById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PutMapping(value = "")
    public ResponseEntity<?> update(@RequestBody Task task) {
        taskService.update(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
