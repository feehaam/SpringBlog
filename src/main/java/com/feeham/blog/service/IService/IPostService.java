package com.feeham.blog.service.IService;

import com.feeham.blog.DTO.PostCreateDTO;
import com.feeham.blog.DTO.PostReadDTO;
import com.feeham.blog.DTO.PostUpdateDTO;

import java.util.List;

public interface IPostService {

    /**
     * Post Service:
     * -
     * This interface defines methods for managing post-related operations, including creating,
     * reading, updating, and deleting posts, as well as retrieving a list of all posts and
     * adding tags to posts.
     *  -
     * Note: Optional is not used as the null-pointer exception is checked in the service layer
     * and then thrown as custom exceptions, which are handled by a global exception handler.
     */

    void create(PostCreateDTO postCreateDto);
    PostReadDTO read(Integer postId);
    void update(PostUpdateDTO postUpdateDto);
    void delete(Integer postId);
    List<PostReadDTO> readAll();
    void addTagToPost(Integer postId, Integer tagId);
}
