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
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){

        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<User> result = this.userService.getUserById(id);

        if(result.isPresent()) {
            return new ResponseEntity<>(dtoConverter.fromUser(result.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {

        if(username == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<User> result = this.userService.getUserByUsername(username);

        if(result.isPresent()) {
            return new ResponseEntity<>(dtoConverter.fromUser(result.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "")
    public ResponseEntity<Page<UserDto>> getAllUsers(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort){

        Page<User> users = userService.getAllUsers(page, size, sort);

        if(users == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users.map(user->dtoConverter.fromUser(user)), HttpStatus.OK);
    }

    @GetMapping(value = "/byProject/{id}")
    public ResponseEntity<Page<UserDto>> getAllUsersByProjectAndRoles(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "roles") List<String> roles,
            @PathVariable Long id){

        Page<User> users = userService.getAllUsersByProjectAndRolesIn(page, size, sort, roles, id);

        if(users == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users.map(user->dtoConverter.fromUser(user)), HttpStatus.OK);
    }

    @GetMapping(value = "/withoutProject")
    public ResponseEntity<Page<UserDto>> getAllUsersWithoutProject(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort) {

        Page<User> users = userService.getAllUsersWithoutProject(page, size, sort);

        if(users == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users.map(user->dtoConverter.fromUser(user)), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto){

        if(userDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try{
            userService.saveUser(dtoConverter.toUser(userDto));
        }
        catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto){

        if(userDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        userService.saveUser(dtoConverter.toUser(userDto));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public  ResponseEntity<?> deleteUser(@PathVariable Long id) {

        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        userService.deleteUser(id);

        return  new ResponseEntity<>(HttpStatus.OK);
    }
}


