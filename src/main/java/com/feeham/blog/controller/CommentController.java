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

@RestController
@RequestMapping("/comments")
public class CommentController {
    // Constructor injection of services
    private final ICommentService commentService;

    @Autowired
    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Create a new comment.
     * @param commentCreateDTO The DTO containing comment data to create.
     * @return A ResponseEntity with a message and HTTP status code.
     */
    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentCreateDTO commentCreateDTO) {
        commentService.create(commentCreateDTO);
        return new ResponseEntity<>("Comment created.", HttpStatus.CREATED);
    }

    /**
     * Get a comment by its ID.
     * @param commentId The ID of the comment to retrieve.
     * @return A ResponseEntity with the retrieved comment and HTTP status code.
     * @throws ResourceNotFoundException if the comment does not exist.
     */
    @GetMapping("/{commentId}")
    public ResponseEntity<?> getComment(@PathVariable Integer commentId)
            throws ResourceNotFoundException {
        CommentReadDTO comment = commentService.read(commentId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    /**
     * Update a comment's content.
     * @param commentId The ID of the comment to update.
     * @param content The updated content for the comment.
     * @return A ResponseEntity with a message and HTTP status code.
     * @throws ResourceNotFoundException if the comment does not exist.
     */
    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Integer commentId, @RequestBody String content)
            throws ResourceNotFoundException {
        CommentUpdateDTO commentUpdateDTO = new CommentUpdateDTO(commentId, content);
        commentService.update(commentUpdateDTO);
        return new ResponseEntity<>("Comment updated.", HttpStatus.OK);
    }

    /**
     * Delete a comment by its ID.
     * @param commentId The ID of the comment to delete.
     * @return A ResponseEntity with a message and HTTP status code.
     * @throws ResourceNotFoundException if the comment does not exist.
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer commentId) throws ResourceNotFoundException {
        commentService.delete(commentId);
        return new ResponseEntity<>("Comment deleted.", HttpStatus.GONE);
    }

    /**
     * Get a list of all comments.
     * @return A ResponseEntity with the list of comments and HTTP status code.
     * @throws NoRecordException if no comments are found.
     */
    @GetMapping
    public ResponseEntity<?> getAllComments() throws NoRecordException {
        List<CommentReadDTO> comments = commentService.readAll();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}