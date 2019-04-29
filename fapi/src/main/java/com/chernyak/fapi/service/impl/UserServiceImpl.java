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

import java.sql.Array;
import java.util.*;

@Service
public class UserServiceImpl implements  UserDetailsService, UserService {

    @Value("${backend.server.url}api/v1/users")
    private String backendServerUrl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl, user, User.class).getBody();
    }

    @Override
    public User deleteUser(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/" + id);
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    @Override
    public List<User> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        User[] usersResponse = restTemplate.getForObject(backendServerUrl, User[].class);
        return Arrays.asList(usersResponse);
    }

    @Override
    public List<User> getByProjectId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        User[] usersResponse = restTemplate.getForObject(backendServerUrl + "/getByProjectId/" + id.toString() , User[].class);
        return Arrays.asList(usersResponse);
    }

    @Override
    public List<User> getPage(int page, int size, String sort) {
        RestTemplate restTemplate = new RestTemplate();
        User[] usersResponse = restTemplate.getForObject(backendServerUrl + "/page?" + "page=" + page + "&size=" + size + "&sort=" + sort , User[].class);
        return Arrays.asList(usersResponse);
    }

    @Override
    public User getByUsername(String username){
        RestTemplate restTemplate = new RestTemplate();
        User userResponse = restTemplate.getForObject(backendServerUrl + "/getByUsername/" + username, User.class);
        return  userResponse;
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }


}
