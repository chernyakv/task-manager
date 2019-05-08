package com.chernyak.backend.service.impl;

import com.chernyak.backend.entity.Comment;
import com.chernyak.backend.repository.CommentRepository;
import com.chernyak.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    public CommentServiceImpl() {
        super();
    }

    @Override
    public Page<Comment> getCommentsByTaskId(int page, int count, String sort, Long taskId) {
        Pageable pageRequest = PageRequest.of(page, count, Sort.by(sort));
        return commentRepository.findAllByTaskId(pageRequest, taskId);

    }

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
