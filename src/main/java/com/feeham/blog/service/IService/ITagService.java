package com.feeham.blog.service.IService;

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
    void addPostToTag(Integer tagId, Integer postId);
}
