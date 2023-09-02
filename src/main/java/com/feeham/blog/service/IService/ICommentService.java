package com.feeham.blog.service.IService;

import com.feeham.blog.DTO.CommentCreateDto;
import com.feeham.blog.DTO.CommentReadDto;
import com.feeham.blog.DTO.CommentUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ICommentService {
    void create(CommentCreateDto commentCreateDto);
    Optional<CommentReadDto> read(Integer commentId);
    void update(CommentUpdateDto commentUpdateDto);
    void delete(Integer commentId);
    List<CommentReadDto> readAll();
}
