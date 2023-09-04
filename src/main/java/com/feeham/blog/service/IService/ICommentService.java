package com.feeham.blog.service.IService;

import com.feeham.blog.DTO.CommentCreateDTO;
import com.feeham.blog.DTO.CommentReadDTO;
import com.feeham.blog.DTO.CommentUpdateDTO;

import java.util.List;

public interface ICommentService {

    /**
     * Comment Service:
     * -
     * This interface defines methods for managing comment-related operations, including creating,
     * reading, updating, and deleting comments, as well as retrieving a list of all comments.
     *  -
     * Note: Optional is not used as the null-pointer exception is checked in the service layer
     * and then thrown as custom exceptions, which are handled by a global exception handler.
     */

    void create(CommentCreateDTO commentCreateDto);
    CommentReadDTO read(Integer commentId);
    void update(CommentUpdateDTO commentUpdateDto);
    void delete(Integer commentId);
    List<CommentReadDTO> readAll();
}
