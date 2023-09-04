package com.feeham.blog.controller;

import com.feeham.blog.DTO.PostCreateDTO;
import com.feeham.blog.DTO.PostReadDTO;
import com.feeham.blog.DTO.PostUpdateDTO;
import com.feeham.blog.exceptions.NoRecordException;
import com.feeham.blog.exceptions.ResourceNotFoundException;
import com.feeham.blog.service.IService.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    // Constructor injection of services
    private final IPostService postService;

    @Autowired
    public PostController(IPostService postService) {
        this.postService = postService;
    }

    /**
     * Create a new post.
     * @param postCreateDTO The DTO containing post data to create.
     * @return A ResponseEntity with a message and HTTP status code.
     * @throws ResourceNotFoundException if the author of the post does not exist.
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PostCreateDTO postCreateDTO) throws ResourceNotFoundException {
        postService.create(postCreateDTO);
        return new ResponseEntity<>("Post created successfully", HttpStatus.CREATED);
    }

    /**
     * Read a post by its ID.
     * @param postId The ID of the post to retrieve.
     * @return A ResponseEntity with the retrieved post and HTTP status code.
     * @throws ResourceNotFoundException if the post does not exist.
     */
    @GetMapping("/{postId}")
    public ResponseEntity<?> read(@PathVariable Integer postId) throws ResourceNotFoundException {
        PostReadDTO postReadDTO = postService.read(postId);
        return new ResponseEntity<>(postReadDTO, HttpStatus.OK);
    }

    /**
     * Update a post's content.
     * @param postId        The ID of the post to update.
     * @param postUpdateDTO The DTO containing post data to update.
     * @return A ResponseEntity with a message and HTTP status code.
     * @throws ResourceNotFoundException if the post does not exist.
     */
    @PutMapping("/{postId}")
    public ResponseEntity<?> update(@PathVariable Integer postId, @RequestBody PostUpdateDTO postUpdateDTO) throws ResourceNotFoundException {
        postUpdateDTO.setId(postId);
        postService.update(postUpdateDTO);
        return new ResponseEntity<>("Post updated successfully", HttpStatus.OK);
    }

    /**
     * Delete a post by its ID.
     * @param postId The ID of the post to delete.
     * @return A ResponseEntity with a message and HTTP status code.
     * @throws ResourceNotFoundException if the post does not exist.
     */
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> delete(@PathVariable Integer postId) throws ResourceNotFoundException {
        postService.delete(postId);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }

    /**
     * Get a list of all posts.
     * @return A ResponseEntity with the list of posts and HTTP status code.
     * @throws NoRecordException if no posts are found.
     */
    @GetMapping
    public ResponseEntity<?> readAll() throws NoRecordException {
        List<PostReadDTO> postReadDTOList = postService.readAll();
        return new ResponseEntity<>(postReadDTOList, HttpStatus.OK);
    }

    /**
     * Add a tag to a post.
     * @param postId The ID of the post to add the tag to.
     * @param tagId  The ID of the tag to add.
     * @return A ResponseEntity with a message and HTTP status code.
     * @throws ResourceNotFoundException if the post does not exist.
     */
    @PostMapping("/{postId}/tags/{tagId}")
    public ResponseEntity<?> addTagToPost(@PathVariable Integer postId, @PathVariable Integer tagId) throws ResourceNotFoundException {
        postService.addTagToPost(postId, tagId);
        return new ResponseEntity<>("Tag added to post successfully", HttpStatus.OK);
    }
}
