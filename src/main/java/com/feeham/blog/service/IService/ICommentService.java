package com.feeham.blog.service.IService;

import com.feeham.blog.DTO.CommentCreateDTO;
import com.feeham.blog.DTO.CommentReadDTO;
import com.feeham.blog.DTO.CommentUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface ICommentService {
    void create(CommentCreateDTO commentCreateDto);
    CommentReadDTO read(Integer commentId);
    void update(CommentUpdateDTO commentUpdateDto);
    void delete(Integer commentId);
    List<CommentReadDTO> readAll();
}
