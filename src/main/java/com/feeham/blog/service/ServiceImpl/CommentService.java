package com.feeham.blog.service.ServiceImpl;

import com.feeham.blog.DTO.CommentCreateDTO;
import com.feeham.blog.DTO.CommentReadDTO;
import com.feeham.blog.DTO.CommentUpdateDTO;
import com.feeham.blog.entity.Comment;
import com.feeham.blog.exceptions.NoRecordException;
import com.feeham.blog.exceptions.ResourceNotFoundException;
import com.feeham.blog.repository.CommentRepository;
import com.feeham.blog.service.IService.ICommentService;
import com.feeham.blog.service.ServiceImpl.helper.ManualMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;
    private final ManualMapper manualMapper;

    public CommentService(CommentRepository commentRepository, ManualMapper manualMapper){
        this.commentRepository = commentRepository;
        this.manualMapper = manualMapper;
    }

    @Override
    public void create(CommentCreateDTO commentCreateDto) {
        commentRepository.save(manualMapper.CommentCreateDTOtoComment(commentCreateDto));
    }

    @Override
    public CommentReadDTO read(Integer commentId) throws ResourceNotFoundException{
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if(commentOptional.isEmpty()){
            throw new ResourceNotFoundException("Comment not found", "Get comment","Comment with ID " + commentId + " does not exist.");
        }
        return manualMapper.CommentToCommentReadDTO(commentOptional.get());
    }

    @Override
    public void update(CommentUpdateDTO commentUpdateDto) throws ResourceNotFoundException{
        Optional<Comment> commentOptional = commentRepository.findById(commentUpdateDto.getCommentId());
        Comment comment = commentOptional.orElseThrow(() -> new ResourceNotFoundException("Comment not found",
                        "Update comment", "Comment with ID " + commentUpdateDto.getCommentId() + " does not exist."));
        comment.setContent(commentUpdateDto.getContent());
        commentRepository.save(comment);
    }

    @Override
    public void delete(Integer commentId) throws ResourceNotFoundException{
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if(commentOptional.isEmpty()){
            throw new ResourceNotFoundException("Failed to delete", "Delete comment","Comment with ID " + commentId + " does not exist.");
        }
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentReadDTO> readAll() throws NoRecordException{
        if(commentRepository.findAll().isEmpty()){
            throw new NoRecordException("No records found", "List of comments", "No comments in the database");
        }
        return commentRepository.findAll().stream()
                .map(manualMapper::CommentToCommentReadDTO)
                .collect(Collectors.toList());
    }
}
