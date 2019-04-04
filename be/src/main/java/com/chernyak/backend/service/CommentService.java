package com.chernyak.backend.service;

import com.chernyak.backend.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();
}
