package com.chernyak.fapi.service;

import com.chernyak.fapi.models.Comment;

public interface CommentService {
    Object getAllCommentsByTaskId(int page, int count, String sort, Long projectId);
    Comment saveComment(Comment comment);
}
