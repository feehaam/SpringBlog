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

@RestController
@RequestMapping("/tags")
public class TagController {
    // Constructor injection of servicess
    private final ITagService tagService;

    @Autowired
    public TagController(ITagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Create a new tag.
     * @param tag The name of the tag to create.
     * @return A ResponseEntity with a message and HTTP status code.
     */
    @PostMapping
    public ResponseEntity<?> createTag(@RequestBody String tag) {
        tagService.create(tag);
        return new ResponseEntity<>("Tag created.", HttpStatus.CREATED);
    }

    /**
     * Get a tag by its ID.
     * @param tagId The ID of the tag to retrieve.
     * @return A ResponseEntity with the retrieved tag and HTTP status code.
     * @throws ResourceNotFoundException if the tag does not exist.
     */
    @GetMapping("/{tagId}")
    public ResponseEntity<?> getTag(@PathVariable Integer tagId) throws ResourceNotFoundException {
        Tag tag = tagService.read(tagId);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    /**
     * Update a tag's name.
     * @param tagId    The ID of the tag to update.
     * @param tagName  The new name of the tag.
     * @return A ResponseEntity with a message and HTTP status code.
     * @throws ResourceNotFoundException if the tag does not exist.
     */
    @PutMapping("/{tagId}")
    public ResponseEntity<?> updateTag(@PathVariable Integer tagId, @RequestBody String tagName) throws ResourceNotFoundException {
        tagService.update(tagId, tagName);
        return new ResponseEntity<>("Tag updated.", HttpStatus.OK);
    }

    /**
     * Delete a tag by its ID.
     * @param tagId The ID of the tag to delete.
     * @return A ResponseEntity with a message and HTTP status code.
     * @throws ResourceNotFoundException if the tag does not exist.
     */
    @DeleteMapping("/{tagId}")
    public ResponseEntity<?> deleteTag(@PathVariable Integer tagId) throws ResourceNotFoundException {
        tagService.delete(tagId);
        return new ResponseEntity<>("Tag deleted.", HttpStatus.GONE);
    }

    /**
     * Get a list of all tags.
     * @return A ResponseEntity with the list of tags and HTTP status code.
     * @throws NoRecordException if no tags are found.
     */
    @GetMapping
    public ResponseEntity<?> getAllTags() throws NoRecordException {
        List<Tag> tags = tagService.readAll();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    /**
     * Get posts associated with a tag by its ID.
     * @param tagId The ID of the tag to retrieve posts for.
     * @return A ResponseEntity with the list of posts and HTTP status code.
     * @throws ResourceNotFoundException if the tag does not exist.
     * @throws NoRecordException if no posts are found for the tag.
     */
    @GetMapping("/{tagId}/posts")
    public ResponseEntity<?> getPostsByTagId(@PathVariable Integer tagId)
            throws ResourceNotFoundException, NoRecordException {
        List<PostReadDTO> posts = tagService.getPostsByTagId(tagId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    /**
     * Get posts associated with a tag by its name.
     * @param tagName The name of the tag to retrieve posts for.
     * @return A ResponseEntity with the list of posts and HTTP status code.
     * @throws ResourceNotFoundException if the tag does not exist.
     * @throws NoRecordException if no posts are found for the tag.
     */
    @GetMapping("/by-tag/{tagName}/posts")
    public ResponseEntity<?> getPostsByTagName(@PathVariable String tagName)
            throws ResourceNotFoundException, NoRecordException {
        List<PostReadDTO> posts = tagService.getPostsByTagName(tagName);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
