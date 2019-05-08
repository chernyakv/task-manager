package com.chernyak.backend.service.impl;

import com.chernyak.backend.entity.Project;
import com.chernyak.backend.entity.User;
import com.chernyak.backend.repository.RoleRepository;
import com.chernyak.backend.repository.UserRepository;
import com.chernyak.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    public Page<User> getAllUsersByProject(int page, int count, String sort, Long projectId) {
        Pageable pageRequest = PageRequest.of(page, count, Sort.by(sort));
        return userRepository.findAllByProjectId(pageRequest, projectId);
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

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
