package com.feeham.blog.controller;

import com.feeham.blog.DTO.CommentCreateDTO;
import com.feeham.blog.DTO.CommentReadDTO;
import com.feeham.blog.DTO.CommentUpdateDTO;
import com.feeham.blog.service.ServiceImpl.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public void createComment(@RequestBody CommentCreateDTO commentCreateDTO) {
        commentService.create(commentCreateDTO);
    }

    @GetMapping("/{commentId}")
    public Optional<CommentReadDTO> getComment(@PathVariable Integer commentId) {
        return commentService.read(commentId);
    }

    @PutMapping("/{commentId}")
    public void updateComment(@PathVariable Integer commentId, @RequestBody String content) {
        commentService.update(new CommentUpdateDTO(commentId, content));
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Integer commentId) {
        commentService.delete(commentId);
    }

    @GetMapping
    public List<CommentReadDTO> getAllComments() {
        return commentService.readAll();
    }
}
