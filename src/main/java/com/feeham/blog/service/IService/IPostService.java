package com.feeham.blog.service.IService;

import com.feeham.blog.DTO.PostCreateDTO;
import com.feeham.blog.DTO.PostReadDTO;
import com.feeham.blog.DTO.PostUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    void create(PostCreateDTO postCreateDto);
    Optional<PostReadDTO> read(Integer postId);
    void update(PostUpdateDTO postUpdateDto);
    void delete(Integer postId);
    List<PostReadDTO> readAll();

    void addTagToPost(Integer postId, Integer tagId);
}
