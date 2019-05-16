package com.chernyak.backend.controller;

import com.chernyak.backend.dto.DtoConverter;
import com.chernyak.backend.dto.ProjectDto;
import com.chernyak.backend.entity.Project;
import com.chernyak.backend.repository.UserRepository;
import com.chernyak.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private ProjectService projectService;
    private DtoConverter dtoConverter;

    @Autowired
    public ProjectController(ProjectService projectService, DtoConverter dtoConverter) {
        this.projectService = projectService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectDto> getById(@PathVariable Long id){

        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Project> result = projectService.getProjectById(id);

        if(result.isPresent()) {
            return new ResponseEntity<>(dtoConverter.fromProject(result.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "")
    public ResponseEntity<Page<ProjectDto>> findAllProjects(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort) {

        Page<Project> result = projectService.getAllProjects(page, size, sort);

        if(result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result.map(project -> dtoConverter.fromProject(project)), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveProject(@RequestBody ProjectDto projectDto) {

        if(projectDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Project project = dtoConverter.toProject(projectDto);

        try{
            projectService.saveProject(project);
        }
        catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateTask(@RequestBody ProjectDto projectDto){

        if(projectDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        projectService.saveProject(dtoConverter.toProject(projectDto));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public  ResponseEntity<?> deleteProject(@PathVariable Long id) {

        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        projectService.deleteProject(id);

        return  new ResponseEntity<>(HttpStatus.OK);
    }


}
