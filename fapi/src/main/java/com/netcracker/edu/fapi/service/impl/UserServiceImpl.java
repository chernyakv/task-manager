package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service("customUserDetailsService")
public class UserServiceImpl implements  UserService {

    @Value("${backend.server.url}api/user")
    private String backendServerUrl;




    @Override
    public  User getById(Long userId){
        RestTemplate restTemplate = new RestTemplate();
        User userResponse = restTemplate.getForObject(backendServerUrl + "/1", User.class);
        return  userResponse;
    }

    @Override
    public List<User> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        User[] usersResponse = restTemplate.getForObject(backendServerUrl + "/all", User[].class);
        return  Arrays.asList(usersResponse);
    }

    @Override
    public User getByUsername(String username){
        RestTemplate restTemplate = new RestTemplate();
        User userResponse = restTemplate.getForObject(backendServerUrl + "/getByUsername/" + username, User.class);
        return  userResponse;
    }

}
