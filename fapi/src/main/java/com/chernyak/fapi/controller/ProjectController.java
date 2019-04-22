package com.chernyak.fapi.controller;

import com.chernyak.fapi.models.Project;
import com.chernyak.fapi.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping(value = "")
    public ResponseEntity<?> save(@RequestBody Project project) {
        projectService.save(project);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Project>> getAll(){
        List<Project> projects = projectService.getAll();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
}
