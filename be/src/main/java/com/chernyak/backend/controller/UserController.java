package com.chernyak.backend.controller;

import com.chernyak.backend.entity.User;

import com.chernyak.backend.repository.UserRepository;
import com.chernyak.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username){
        if(username == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = this.userService.getByUsername(username);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }



    @GetMapping(value = "/all")
    public List<User> findAll(){
        return (List<User>) userService.getAll();
    }


}


