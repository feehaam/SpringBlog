package com.feeham.blog.service.ServiceImpl;

import com.feeham.blog.DTO.CommentCreateDTO;
import com.feeham.blog.DTO.CommentReadDTO;
import com.feeham.blog.DTO.CommentUpdateDTO;
import com.feeham.blog.entity.Comment;
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
    public Optional<CommentReadDTO> read(Integer commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        return commentOptional.map(manualMapper::CommentToCommentReadDTO).or(() -> Optional.of(null));
    }

    @Override
    public void update(CommentUpdateDTO commentUpdateDto) {
        Optional<Comment> commentOptional = commentRepository.findById(commentUpdateDto.getCommentId());
        if(commentOptional.isPresent()){
            Comment comment = commentOptional.get();
            comment.setContent(commentUpdateDto.getContent());
            commentRepository.save(comment);
        }
    }

    @Override
    public void delete(Integer commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentReadDTO> readAll() {
        return commentRepository.findAll().stream()
                .map(manualMapper::CommentToCommentReadDTO)
                .collect(Collectors.toList());
    }
}
