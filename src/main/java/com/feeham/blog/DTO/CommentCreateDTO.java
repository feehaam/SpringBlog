package com.feeham.blog.DTO;

import org.springframework.stereotype.Component;

// Comment data transfer object for Creating a Comment
@Component
public class CommentCreateDTO {
    private Integer userId;
    private Integer postId;
    private Integer parentCommentId;
    private String content;

    public CommentCreateDTO(){

    }

    public CommentCreateDTO(Integer userId, Integer postId, Integer parentCommentId, String content) {
        this.userId = userId;
        this.postId = postId;
        this.parentCommentId = parentCommentId;
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
