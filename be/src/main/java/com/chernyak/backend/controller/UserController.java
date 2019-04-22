package com.chernyak.backend.controller;

import com.chernyak.backend.dto.DtoConverter;
import com.chernyak.backend.dto.ProjectDto;
import com.chernyak.backend.dto.UserDto;
import com.chernyak.backend.entity.User;

import com.chernyak.backend.repository.UserRepository;
import com.chernyak.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    private UserService userService;
    private DtoConverter dtoConverter;

    @Autowired
    public UserController(UserService userService, DtoConverter dtoConverter){
        this.userService = userService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){

        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User result = this.userService.findById(id);

        if(result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<UserDto>> findAll(){

        List<User> users = userService.getAll();

        if(users == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UserDto> result = new ArrayList<UserDto>();
        users.forEach(user -> {
            UserDto userDto = dtoConverter.fromUser(user);
            result.add(userDto);
        });

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<List<UserDto>> findPage(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort) {

        List<User> pageUsers = userService.getPage(page, size, sort).getContent();

        if(pageUsers == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UserDto> result = new ArrayList<UserDto>();
        pageUsers.forEach(user -> {
            UserDto userDto = dtoConverter.fromUser(user);
            result.add(userDto);
        });

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/getByUsername/{username}")
    public ResponseEntity<UserDto> getByUsername(@PathVariable String username) {
        if(username == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.findByUsername(username);

        if(user == null) {
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto userDto = dtoConverter.fromUser(user);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto){

        if(userDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = dtoConverter.toUser(userDto);

        try{
            userService.save(user);
        }
        catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto){

        if(userDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = dtoConverter.toUser(userDto);

        userService.update(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public  ResponseEntity<?> deleteUser(@PathVariable Long id) {

        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        userService.delete(id);

        return  new ResponseEntity<>(HttpStatus.OK);
    }
}


