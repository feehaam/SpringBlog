package com.feeham.blog.controller;

import com.feeham.blog.DTO.CommentCreateDTO;
import com.feeham.blog.DTO.CommentReadDTO;
import com.feeham.blog.DTO.CommentUpdateDTO;
import com.feeham.blog.service.IService.ICommentService;
import com.feeham.blog.exceptions.NoRecordException;
import com.feeham.blog.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final ICommentService commentService;

    @Autowired
    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentCreateDTO commentCreateDTO) {
        commentService.create(commentCreateDTO);
        return new ResponseEntity<>("Comment created.", HttpStatus.CREATED);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<?> getComment(@PathVariable Integer commentId)
            throws ResourceNotFoundException {
        CommentReadDTO comment = commentService.read(commentId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Integer commentId, @RequestBody String content)
            throws ResourceNotFoundException {
        CommentUpdateDTO commentUpdateDTO = new CommentUpdateDTO(commentId, content);
        commentService.update(commentUpdateDTO);
        return new ResponseEntity<>("Comment updated.", HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer commentId) throws ResourceNotFoundException {
        commentService.delete(commentId);
        return new ResponseEntity<>("Comment deleted.", HttpStatus.GONE);
    }

    @GetMapping
    public ResponseEntity<?> getAllComments() throws NoRecordException {
        List<CommentReadDTO> comments = commentService.readAll();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
