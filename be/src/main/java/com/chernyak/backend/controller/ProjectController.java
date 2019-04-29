package com.chernyak.backend.controller;

import com.chernyak.backend.dto.DtoConverter;
import com.chernyak.backend.dto.ProjectDto;
import com.chernyak.backend.entity.Project;
import com.chernyak.backend.repository.UserRepository;
import com.chernyak.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private DtoConverter dtoConverter;

    @PostMapping(value = "")
    public ResponseEntity<?> saveProject(@RequestBody ProjectDto projectDto) {

        if(projectDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Project project = dtoConverter.toProject(projectDto);

        try{
            projectService.save(project);
        }
        catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = "")
    public ResponseEntity<List<ProjectDto>> findAll(){

        List<Project> projects = projectService.getAll();


        if(projects == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<ProjectDto> result = new ArrayList<ProjectDto>();
        projects.forEach(project -> {
            ProjectDto projectDto = dtoConverter.fromProject(project);
            result.add(projectDto);
        });

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectDto> getById(@PathVariable Long id){

        Project project = projectService.getById(id);


        if(project == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ProjectDto projectDto = dtoConverter.fromProject(project);

        return new ResponseEntity<>(projectDto, HttpStatus.OK);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<List<ProjectDto>> findPage(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort) {

        List<Project> pageUsers = projectService.getPage(page, size, sort).getContent();

        if(pageUsers == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<ProjectDto> result = new ArrayList<ProjectDto>();
        pageUsers.forEach(project -> {
            ProjectDto projectDto = dtoConverter.fromProject(project);
            result.add(projectDto);
        });

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public  ResponseEntity<?> deleteProject(@PathVariable Long id) {

        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        projectService.delete(id);

        return  new ResponseEntity<>(HttpStatus.OK);
    }


}
