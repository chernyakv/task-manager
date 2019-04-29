package com.chernyak.fapi.controller;

import com.chernyak.fapi.models.User;
import com.chernyak.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserService userService;

    @GetMapping(value = "/page")
    public List<User> getPage(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort) {
        return userService.getPage(page, size, sort);
    }

    @GetMapping(value = "/getByProjectId/{id}")
    public List<User> getByProjectId(@PathVariable Long id){
        return userService.getByProjectId(id);
    }

    @GetMapping(value = "")
    public List<User> getAllUsers(){
            return userService.getAll();
    }

    @PostMapping(value = "")
    public ResponseEntity<?> save(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?>  delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
