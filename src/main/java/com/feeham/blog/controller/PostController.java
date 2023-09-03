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
    public ResponseEntity<?> create(@RequestBody PostCreateDTO postCreateDTO) throws ResourceNotFoundException {
        postService.create(postCreateDTO);
        return new ResponseEntity<>("Post created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> read(@PathVariable Integer postId) throws ResourceNotFoundException {
        PostReadDTO postReadDTO = postService.read(postId);
        return new ResponseEntity<>(postReadDTO, HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> update(@PathVariable Integer postId, @RequestBody PostUpdateDTO postUpdateDTO) throws ResourceNotFoundException {
        postUpdateDTO.setId(postId);
        postService.update(postUpdateDTO);
        return new ResponseEntity<>("Post updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> delete(@PathVariable Integer postId) throws ResourceNotFoundException {
        postService.delete(postId);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> readAll() throws NoRecordException {
        List<PostReadDTO> postReadDTOList = postService.readAll();
        return new ResponseEntity<>(postReadDTOList, HttpStatus.OK);
    }

    @PostMapping("/{postId}/tags/{tagId}")
    public ResponseEntity<?> addTagToPost(@PathVariable Integer postId, @PathVariable Integer tagId) throws ResourceNotFoundException {
        postService.addTagToPost(postId, tagId);
        return new ResponseEntity<>("Tag added to post successfully", HttpStatus.OK);
    }
}
