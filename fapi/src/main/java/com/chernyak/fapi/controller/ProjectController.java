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
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id){
        return new ResponseEntity<>(projectService.getProjectById(id), HttpStatus.OK);
    }

    @GetMapping
    public Object getAllProjects(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort) {
        return projectService.getAllProjects(page, size, sort);
    }

    @PostMapping(value = "")
    public ResponseEntity<Project> saveProject(@RequestBody Project project) {
        return new ResponseEntity<>(projectService.saveProject(project), HttpStatus.OK);
    }

    @PutMapping(value = "")
    public ResponseEntity<Project> updateProject(@RequestBody Project project) {
        return new ResponseEntity<>(projectService.updateProject(project), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?>  deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
