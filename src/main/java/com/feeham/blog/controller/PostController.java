package com.feeham.blog.controller;

import com.feeham.blog.DTO.PostCreateDTO;
import com.feeham.blog.DTO.PostReadDTO;
import com.feeham.blog.DTO.PostUpdateDTO;
import com.feeham.blog.service.IService.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final IPostService postService;

    @Autowired
    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostCreateDTO postCreateDTO) {
        postService.create(postCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostReadDTO> getPost(@PathVariable Integer postId) {
        Optional<PostReadDTO> post = postService.read(postId);
        return post.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable Integer postId, @RequestBody PostUpdateDTO postUpdateDTO) {
        postUpdateDTO.setId(postId); // Set the ID from the URL into the DTO
        postService.update(postUpdateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer postId) {
        postService.delete(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<PostReadDTO>> getAllPosts() {
        List<PostReadDTO> posts = postService.readAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("/{tagId}/add-to-post/{postId}")
    public void addTagToPost(@PathVariable Integer tagId, @PathVariable Integer postId) {
        postService.addTagToPost(tagId, postId);
    }
}
