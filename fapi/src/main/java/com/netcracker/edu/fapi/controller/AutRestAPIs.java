package com.netcracker.edu.fapi.controller;


import com.netcracker.edu.fapi.message.LoginForm;
import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AutRestAPIs {


    private UserService userService;

    @Autowired
    public  AutRestAPIs(UserService userService){
        this.userService = userService;
    }


    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginRequest) {
        User user = userService.getByUsername(loginRequest.getUsername());

        return ResponseEntity.ok(user);
    }


}
