package com.chernyak.backend.service;

import com.chernyak.backend.entity.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {
    Page<Comment> getCommentsByTaskId(int page, int count, String sort, Long taskId);
    Comment saveComment(Comment comment);
}
