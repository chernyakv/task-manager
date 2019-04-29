package com.chernyak.backend.controller;


import com.chernyak.backend.dto.DtoConverter;
import com.chernyak.backend.dto.TaskDto;
import com.chernyak.backend.dto.UserDto;
import com.chernyak.backend.entity.Task;
import com.chernyak.backend.service.TaskService;
import com.chernyak.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {


    private TaskService taskService;
    private DtoConverter dtoConverter;

    @Autowired
    public TaskController(TaskService taskService, DtoConverter dtoConverter){
        this.taskService = taskService;
        this.dtoConverter = dtoConverter;
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveTask(@RequestBody TaskDto taskDto){

        if(taskDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Task task = dtoConverter.toTask(taskDto);

        try{
            taskService.save(task);
        }
        catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getByAssigneeUsername/{username}")
    public ResponseEntity<List<TaskDto>> findAll(@PathVariable String username){


        List<Task> tasks = taskService.getTaskByAsigneeUsername(username);

        if(tasks == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<TaskDto> result = new ArrayList<TaskDto>();
        tasks.forEach(task -> {
            TaskDto taskDto = dtoConverter.fromTask(task);
            result.add(taskDto);
        });

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/getByProjectId/{projectId}")
    public ResponseEntity<List<TaskDto>> findByProjectId(@PathVariable String projectId){


        List<Task> tasks = taskService.getTasksByProjectId(projectId);

        if(tasks == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<TaskDto> result = new ArrayList<TaskDto>();
        tasks.forEach(task -> {
            TaskDto taskDto = dtoConverter.fromTask(task);
            result.add(taskDto);
        });

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable Long id){

        Task task = taskService.getById(id);


        if(task == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        TaskDto taskDto = dtoConverter.fromTask(task);

        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateTask(@RequestBody TaskDto taskDto){

        if(taskDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Task task = dtoConverter.toTask(taskDto);

        taskService.update(task);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/page/{id}")
    public ResponseEntity<Page<TaskDto>> findPage(
            @PathVariable Long id,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort) {

        Page<Task> pageTasks = taskService.getTasksByProjectId1(id,page, size, sort);

        if(pageTasks == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(pageTasks.map(task -> dtoConverter.fromTask(task)), HttpStatus.OK);
    }




}
