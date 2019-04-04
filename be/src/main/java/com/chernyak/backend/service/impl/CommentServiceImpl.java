package com.chernyak.backend.service.impl;

import com.chernyak.backend.entity.Comment;
import com.chernyak.backend.repository.CommentRepository;
import com.chernyak.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> getAll(){
        return commentRepository.findAll();
    }
}
