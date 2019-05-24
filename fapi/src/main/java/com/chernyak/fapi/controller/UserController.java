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
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public  ResponseEntity<User> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/username/{username}")
    public  ResponseEntity<User> getUserById(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<Object>  getAllUsers(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort) {
        return new ResponseEntity<>(userService.getAllUsers(page, size, sort), HttpStatus.OK);
    }

    @GetMapping(value = "/byProject/{id}")
    public ResponseEntity<Object>  getAllUsersByProject(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "roles") List<String> roles,
            @PathVariable Long id) {
        return new ResponseEntity<>(userService.getAllUsersByProject(page, size, sort, roles, id), HttpStatus.OK);
    }

    @GetMapping(value = "/withoutProject")
    public ResponseEntity<Object>  getAllUsersWithoutProject(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort) {
        return new ResponseEntity<>(userService.getAllUsersWithoutProject(page, size, sort), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?>  delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
