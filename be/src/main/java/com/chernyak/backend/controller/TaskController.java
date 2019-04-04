package com.chernyak.backend.controller;


import com.chernyak.backend.entity.Task;
import com.chernyak.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Task>> getUser(@PathVariable(name = "id") Long id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Task> tasks = this.taskService.getTaskByAsigneeId(id);

        if(tasks == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


}
