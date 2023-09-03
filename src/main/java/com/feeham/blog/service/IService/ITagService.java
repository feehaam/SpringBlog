package com.feeham.blog.service.IService;

import com.feeham.blog.DTO.PostReadDTO;
import com.feeham.blog.entity.Post;
import com.feeham.blog.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface ITagService {
    void create(String tag);
    Optional<Tag> read(Integer tagId);
    void update(Integer tagId, String tag);
    void delete(Integer tagId);
    List<Tag> readAll();
    List<PostReadDTO> getPostsByTagId(Integer tagId);
    List<PostReadDTO> getPostsByTagName(String tag);
}
