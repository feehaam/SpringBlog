package com.feeham.blog.service.IService;

import com.feeham.blog.DTO.CommentCreateDto;
import com.feeham.blog.DTO.CommentUpdateDto;
import com.feeham.blog.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface ICommentService {
    void create(CommentCreateDto commentCreateDto);
    Optional<Comment> read(Integer commentId);
    void update(CommentUpdateDto commentUpdateDto);
    void delete(Integer commentId);
    List<Comment> readAll();
}
