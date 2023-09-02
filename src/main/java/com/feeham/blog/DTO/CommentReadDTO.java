package com.feeham.blog.DTO;

import com.feeham.blog.entity.Comment;

import java.time.LocalDateTime;
import java.util.List;

public class CommentReadDTO {
    private Integer id;
    private String content;
    private LocalDateTime timeCreated;
    private Integer upVotes;
    private Integer downVotes;
    private Integer userID;
    private String userFullName;
    private String userEmail;
    private Integer parentPostId;
    private Integer parentCommentId;
    private List<Comment> replies;
}