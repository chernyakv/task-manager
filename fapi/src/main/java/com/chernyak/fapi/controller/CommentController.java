package com.chernyak.fapi.controller;

import com.chernyak.fapi.models.Comment;
import com.chernyak.fapi.models.User;
import com.chernyak.fapi.service.CommentService;
import com.chernyak.fapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping(value = "/byTask/{id}")
    public ResponseEntity<Object> getAllUsersByTask(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort,
            @PathVariable Long id) {
        return new ResponseEntity<>(commentService.getAllCommentsByTaskId(page, size, sort, id), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        return new ResponseEntity<>(commentService.saveComment(comment), HttpStatus.OK);
    }
}
