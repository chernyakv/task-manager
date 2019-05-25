package com.chernyak.backend.controller;


import com.chernyak.backend.dto.DtoConverter;
import com.chernyak.backend.dto.TaskDto;
import com.chernyak.backend.dto.UserDto;
import com.chernyak.backend.entity.Task;
import com.chernyak.backend.entity.enums.TaskStatus;
import com.chernyak.backend.repository.Specifications.TaskSpecifications;
import com.chernyak.backend.service.TaskService;
import com.chernyak.backend.service.UserService;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable Long id){

        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Task> result = taskService.getTaskById(id);

        if(result.isPresent()) {
            return new ResponseEntity<>(dtoConverter.fromTask(result.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/byAssignee/{username}")
    public ResponseEntity<Page<TaskDto>> findAllTasksByAsigneeUsername(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "order") String order,
            @PathVariable String username) {

        Specification<Task> spec = TaskSpecifications.filter("OPEN", "NORMAL");
        Page<Task> tasks1 = taskService.findAll(page, size, sort, order, spec);

        Page<Task> tasks = taskService.getTaskByAsigneeUsername(page, size, sort, order, username);

        if(tasks == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tasks.map(task -> dtoConverter.fromTask(task)), HttpStatus.OK);
    }

    @GetMapping(value = "/test")
    public ResponseEntity<Page<TaskDto>> findAll(
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "priority", required = false) String priority,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "order") String order,
            @PathVariable Long id) {

        Specification<Task> spec = TaskSpecifications.filter(status, priority);
        Page<Task> tasks = taskService.findAll(page, size, sort, order, spec);

        if(tasks == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tasks.map(task -> dtoConverter.fromTask(task)), HttpStatus.OK);
    }

    @GetMapping(value = "/byProject/{id}")
    public ResponseEntity<Page<TaskDto>> findAllTasksByProject(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "order") String order,
            @PathVariable Long id) {

        Page<Task> tasks = taskService.getTasksByProjectId(page, size, sort, order, id);

        if(tasks == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tasks.map(task -> dtoConverter.fromTask(task)), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveTask(@RequestBody TaskDto taskDto){

        if(taskDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try{
            taskService.saveTask(dtoConverter.toTask(taskDto));
        }
        catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateTask(@RequestBody TaskDto taskDto){

        if(taskDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        taskService.saveTask(dtoConverter.toTask(taskDto));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public  ResponseEntity<?> deleteTask(@PathVariable Long id) {

        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        taskService.deleteTask(id);

        return  new ResponseEntity<>(HttpStatus.OK);
    }
}

