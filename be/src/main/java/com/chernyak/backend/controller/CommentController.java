package com.chernyak.backend.controller;

import com.chernyak.backend.dto.CommentDto;
import com.chernyak.backend.dto.DtoConverter;
import com.chernyak.backend.entity.Comment;
import com.chernyak.backend.service.CommentService;
import com.chernyak.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private CommentService commentService;
    private DtoConverter dtoConverter;

    @Autowired
    public CommentController(CommentService commentService,  DtoConverter dtoConverter){
        this.commentService = commentService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping(value = "/byTask/{id}")
    public ResponseEntity<Page<CommentDto>> getAllCommentsByTask(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort,
            @PathVariable Long id){

        Page<Comment> comments = commentService.getCommentsByTaskId(page, size, sort, id);

        if(comments == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(comments.map(comment->dtoConverter.fromComment(comment)), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveComment(@RequestBody CommentDto commentDto){

        if(commentDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try{
            commentService.saveComment(dtoConverter.toComment(commentDto));
        }
        catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
