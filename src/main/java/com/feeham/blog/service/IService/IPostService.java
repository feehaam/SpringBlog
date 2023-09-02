package com.feeham.blog.service.IService;

import com.feeham.blog.DTO.PostCreateDto;
import com.feeham.blog.DTO.PostUpdateDto;
import com.feeham.blog.entity.Post;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    void create(PostCreateDto postCreateDto);
    Optional<Post> read(Integer postId);
    void update(PostUpdateDto postUpdateDto);
    void delete(Integer postId);
    List<Post> readAll();
}
