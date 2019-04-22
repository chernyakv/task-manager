package com.chernyak.backend.service.impl;

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

import java.util.List;

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
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        return result;
    }

    @Override
    public User save(User user) {
        if(user.getRoles()!=null){
            user.getRoles().forEach(role->
                    role.setId(roleRepository.findByName(role.getName()).getId()));
        }

        User savedUser = userRepository.save(user);

        return savedUser;
    }

    @Override
    public User update(User user) {
        User result = userRepository.findByUsername(user.getUsername());
        user.setId(result.getId());

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        return result;
    }

    @Override
    public Page<User> getPage(int page, int count, String sort) {
        Pageable pageRequest = PageRequest.of(page, count);
        return userRepository.findAll(pageRequest);
    }
}
