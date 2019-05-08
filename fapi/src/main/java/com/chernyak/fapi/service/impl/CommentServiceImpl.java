package com.chernyak.fapi.service.impl;

import com.chernyak.fapi.models.Comment;
import com.chernyak.fapi.service.CommentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommentServiceImpl implements CommentService {

    @Value("${backend.server.url}api/v1/comments")
    private String backendServerUrl;

    @Override
    public Object getAllCommentsByTaskId(int page, int size, String sort, Long taskId) {
        RestTemplate restTemplate = new RestTemplate();
        Object usersResponse = restTemplate.getForObject(backendServerUrl + "/byTask/" + taskId + "?page=" + page + "&size=" + size + "&sort=" + sort , Object.class);
        return usersResponse;
    }

    @Override
    public Comment saveComment(Comment comment) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl, comment, Comment.class).getBody();
    }
}
