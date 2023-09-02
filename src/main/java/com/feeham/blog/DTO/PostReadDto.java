package com.feeham.blog.DTO;

import com.feeham.blog.entity.Comment;
import com.feeham.blog.entity.Tag;

import java.time.LocalDateTime;
import java.util.List;

public class PostReadDto {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime timeCreated;
    private LocalDateTime timeLastModified;
    private Integer userID;
    private String userFullName;
    private String userEmail;
    private List<Comment> comments;
    private List<Tag> tags;
}
