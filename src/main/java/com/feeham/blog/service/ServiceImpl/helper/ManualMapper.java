package com.feeham.blog.service.ServiceImpl.helper;

import com.feeham.blog.DTO.CommentCreateDTO;
import com.feeham.blog.DTO.CommentReadDTO;
import com.feeham.blog.DTO.PostReadDTO;
import com.feeham.blog.entity.Comment;
import com.feeham.blog.entity.Post;
import com.feeham.blog.repository.CommentRepository;
import com.feeham.blog.repository.PostRepository;
import com.feeham.blog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManualMapper {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public ManualMapper(CommentRepository commentRepository, UserRepository userRepository,
                        PostRepository postRepository){
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public PostReadDTO postToPostReadDTO(Post post){

        PostReadDTO result = new PostReadDTO();
        result.setId(post.getId());
        result.setTitle(post.getTitle());
        result.setContent(post.getContent());
        result.setTimeCreated(post.getTimeCreated());
        result.setTimeLastModified(post.getTimeLastModified());
        result.setUserID(post.getAuthor().getId());
        result.setUserEmail(post.getAuthor().getEmail());
        result.setUserFullName(post.getAuthor().getFirstName() + " " + post.getAuthor().getLastName());
        result.setTags(post.getTags());
        result.setComments(post.getComments());

        return result;
    }

    public Comment CommentCreateDTOtoComment(CommentCreateDTO commentDto){
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        if(commentDto.getParentCommentId() != null && commentDto.getParentCommentId() > 0){
            Comment parentComment = commentRepository.findById(commentDto.getParentCommentId()).get();
            comment.setParentComment(parentComment);
        }
        if(commentDto.getPostId() != null && commentDto.getPostId() > 0){
            Post parentPost = postRepository.findById(commentDto.getPostId()).get();
            comment.setParentPost(parentPost);
        }
        comment.setTimeCreated(LocalDateTime.now());
        comment.setReplies(new ArrayList<>());
        comment.setUser(userRepository.findById(commentDto.getUserId()).get());
        comment.setDownVotes(0);
        comment.setUpVotes(0);

        return comment;
    }

    public CommentReadDTO CommentToCommentReadDTO(Comment comment) {
        CommentReadDTO commentReadDTO = new CommentReadDTO();
        commentReadDTO.setId(comment.getId());
        commentReadDTO.setContent(comment.getContent());
        commentReadDTO.setTimeCreated(comment.getTimeCreated());
        commentReadDTO.setUpVotes(comment.getUpVotes());
        commentReadDTO.setDownVotes(comment.getDownVotes());
        commentReadDTO.setUserID(comment.getUser().getId());
        commentReadDTO.setUserFullName(comment.getUser().getFirstName() +" "+comment.getUser().getLastName());
        commentReadDTO.setUserEmail(comment.getUser().getEmail());
        commentReadDTO.setParentPostId(comment.getParentPost().getId());
        commentReadDTO.setParentCommentId(comment.getParentComment() != null ? comment.getParentComment().getId() : null);

        // Recursively convert each reply
        List<CommentReadDTO> replyDTOs = new ArrayList<>();
        for (Comment reply : comment.getReplies()) {
            CommentReadDTO replyDTO = CommentToCommentReadDTO(reply);
            replyDTOs.add(replyDTO);
        }

        commentReadDTO.setReplies(replyDTOs);

        return commentReadDTO;
    }
}
