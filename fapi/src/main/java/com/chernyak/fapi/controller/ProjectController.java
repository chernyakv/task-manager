package com.chernyak.fapi.controller;

import com.chernyak.fapi.models.Project;
import com.chernyak.fapi.service.ProjectService;
import com.chernyak.fapi.validators.ProjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

    private ProjectService projectService;
    private ProjectValidator projectValidator;

    @Autowired
    public ProjectController(ProjectService projectService, ProjectValidator projectValidator) {
        this.projectService = projectService;
        this.projectValidator = projectValidator;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id){
        return new ResponseEntity<>(projectService.getProjectById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/code/{code}")
    public ResponseEntity<Project> getProjectById(@PathVariable String code){
        return new ResponseEntity<>(projectService.getProjectByCode(code), HttpStatus.OK);
    }

    @GetMapping
    public Object getAllProjects(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "order") String order) {
        return projectService.getAllProjects(page, size, sort, order);
    }

    @PostMapping(value = "")
    @PreAuthorize("hasRole('PROJECT_MANAGER')")
    public ResponseEntity<?> saveProject(@RequestBody Project project, BindingResult bindingResult) {
        projectValidator.validate(project, bindingResult);
        if(bindingResult.hasErrors()){
            return  ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
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
