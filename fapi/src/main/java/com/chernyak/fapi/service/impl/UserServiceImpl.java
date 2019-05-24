package com.chernyak.fapi.service.impl;


import com.chernyak.fapi.models.User;
import com.chernyak.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Array;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Value("${backend.server.url}api/v1/users")
    private String backendServerUrl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUserById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        User userResponse = restTemplate.getForObject(backendServerUrl + "/" + id, User.class);
        return  userResponse;
    }

    @Override
    public User getUserByUsername(String username){
        RestTemplate restTemplate = new RestTemplate();
        User userResponse = restTemplate.getForObject(backendServerUrl + "/username/" + username, User.class);
        return  userResponse;
    }

    @Override
    public Object getAllUsers(int page, int size, String sort) {
        RestTemplate restTemplate = new RestTemplate();
        Object usersResponse = restTemplate.getForObject(backendServerUrl + "?page=" + page + "&size=" + size + "&sort=" + sort , Object.class);
        return usersResponse;
    }

    @Override
    public Object getAllUsersByProject(int page, int size, String sort, List<String> roles, Long projectId) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder uri = UriComponentsBuilder
                .fromHttpUrl(backendServerUrl + "/byProject/" + projectId);

        for (String role : roles) {
            uri.queryParam("roles", role);
        }
        uri.queryParam("page", page);
        uri.queryParam("size", size);
        uri.queryParam("sort", sort);

        Object usersResponse = restTemplate.getForObject(uri.toUriString(), Object.class);
        return usersResponse;
    }

    @Override
    public Object getAllUsersWithoutProject(int page, int size, String sort) {
        RestTemplate restTemplate = new RestTemplate();
        Object usersResponse = restTemplate.getForObject(backendServerUrl + "/withoutProject?" + "page=" + page + "&size=" + size + "&sort=" + sort , Object.class);
        return usersResponse;
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl, user, User.class).getBody();
    }

    @Override
    public void deleteUser(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/" + id);
    }
}
