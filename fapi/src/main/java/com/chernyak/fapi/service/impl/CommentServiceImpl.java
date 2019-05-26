package com.chernyak.fapi.service.impl;

import com.chernyak.fapi.models.Comment;
import com.chernyak.fapi.service.CommentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CommentServiceImpl implements CommentService {

    @Value("${backend.server.url}api/v1/comments")
    private String backendServerUrl;

    @Override
    public Object getAllCommentsByTaskId(int page, int size, String sort, Long taskId) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder uri = UriComponentsBuilder
                .fromHttpUrl(backendServerUrl + "/byTask/" + taskId);
        uri.queryParam("page", page);
        uri.queryParam("size", size);
        uri.queryParam("sort", sort);
        return restTemplate.getForObject(uri.toUriString(), Object.class);
    }

    @Override
    public Comment saveComment(Comment comment) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl, comment, Comment.class).getBody();
    }
}
