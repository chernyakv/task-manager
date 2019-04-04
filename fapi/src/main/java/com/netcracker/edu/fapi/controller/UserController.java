package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long userId){

        User user = userService.getById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
