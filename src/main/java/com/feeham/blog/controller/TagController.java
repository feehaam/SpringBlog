package com.feeham.blog.controller;

import com.feeham.blog.DTO.PostReadDTO;
import com.feeham.blog.entity.Tag;
import com.feeham.blog.service.IService.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createTag(@RequestBody String tagName) {
        tagService.create(tagName);
    }

    @GetMapping("/{tagId}")
    public Optional<Tag> getTag(@PathVariable Integer tagId) {
        return tagService.read(tagId);
    }

    @PutMapping("/{tagId}")
    public void updateTag(@PathVariable Integer tagId, @RequestBody String tagName) {
        tagService.update(tagId, tagName);
    }

    @DeleteMapping("/{tagId}")
    public void deleteTag(@PathVariable Integer tagId) {
        tagService.delete(tagId);
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.readAll();
    }

    @GetMapping("/{tagId}/posts")
    public List<PostReadDTO> getPostsByTagId(@PathVariable Integer tagId) {
        return tagService.getPostsByTagId(tagId);
    }

    @GetMapping("/by-tag/{tagName}/posts")
    public List<PostReadDTO> getPostsByTagName(@PathVariable String tagName) {
        return tagService.getPostsByTagName(tagName);
    }
}
