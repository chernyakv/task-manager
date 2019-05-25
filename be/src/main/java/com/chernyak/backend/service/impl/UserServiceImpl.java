package com.chernyak.backend.service.impl;

import com.chernyak.backend.entity.Project;
import com.chernyak.backend.entity.Role;
import com.chernyak.backend.entity.User;
import com.chernyak.backend.repository.RoleRepository;
import com.chernyak.backend.repository.UserRepository;
import com.chernyak.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public  UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {

        Optional<User> user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public Page<User> getAllUsers(int page, int count, String sort) {
        Pageable pageRequest = PageRequest.of(page, count, Sort.by(sort));
        return userRepository.findAll(pageRequest);
    }

    @Override
    public Page<User> getAllUsersByProjectAndRolesIn(int page, int count, String sort, List<String> roles, Long projectId) {
        Pageable pageRequest = PageRequest.of(page, count, Sort.by(sort));
        List<Role> convertedRoles = roles.stream().map(role->{
            return roleRepository.findByName(role);
        }).collect(Collectors.toList());

        return userRepository.findAllByProjectIdAndRolesIn(pageRequest, projectId, convertedRoles);
    }

    @Override
    public Page<User> getAllUsersWithoutProject(int page, int count, String sort) {
        Pageable pageRequest = PageRequest.of(page, count, Sort.by(sort));
        return userRepository.findAllByProjectIsNull(pageRequest);
    }

    @Override
    public User saveUser(User user) {
        if(user.getRoles()!=null){
            user.getRoles().forEach(role->
                    role.setId(roleRepository.findByName(role.getName()).getId()));
        }
        //if(userRepository.findByUsername(user.getUsername()).isPresent()){
        //    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
        //            "User with email '" + user.getUsername() + "' already exists");
        //}

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
