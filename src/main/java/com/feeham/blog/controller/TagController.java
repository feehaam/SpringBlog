package com.feeham.blog.controller;

import com.feeham.blog.DTO.PostReadDTO;
import com.feeham.blog.entity.Tag;
import com.feeham.blog.exceptions.NoRecordException;
import com.feeham.blog.exceptions.ResourceNotFoundException;
import com.feeham.blog.service.IService.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final ITagService tagService;

    @Autowired
    public TagController(ITagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseEntity<?> createTag(@RequestBody String tag) {
        tagService.create(tag);
        return new ResponseEntity<>("Tag created.", HttpStatus.CREATED);
    }

    @GetMapping("/{tagId}")
    public ResponseEntity<?> getTag(@PathVariable Integer tagId) throws ResourceNotFoundException {
        Tag tag = tagService.read(tagId);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @PutMapping("/{tagId}")
    public ResponseEntity<?> updateTag(@PathVariable Integer tagId, @RequestBody String tagName) throws ResourceNotFoundException {
        tagService.update(tagId, tagName);
        return new ResponseEntity<>("Tag updated.", HttpStatus.OK);
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<?> deleteTag(@PathVariable Integer tagId) throws ResourceNotFoundException {
        tagService.delete(tagId);
        return new ResponseEntity<>("Tag deleted.", HttpStatus.GONE);
    }

    @GetMapping
    public ResponseEntity<?> getAllTags() throws NoRecordException {
        List<Tag> tags = tagService.readAll();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/{tagId}/posts")
    public ResponseEntity<?> getPostsByTagId(@PathVariable Integer tagId)
            throws ResourceNotFoundException, NoRecordException {
        List<PostReadDTO> posts = tagService.getPostsByTagId(tagId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/by-tag/{tagName}/posts")
    public ResponseEntity<?> getPostsByTagName(@PathVariable String tagName)
            throws ResourceNotFoundException, NoRecordException {
        List<PostReadDTO> posts = tagService.getPostsByTagName(tagName);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
