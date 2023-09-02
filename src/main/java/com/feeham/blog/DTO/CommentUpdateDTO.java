package com.feeham.blog.DTO;

import org.springframework.stereotype.Component;

@Component
public class CommentUpdateDTO {
    private Integer commentId;
    private String content;

    public CommentUpdateDTO(){

    }

    public CommentUpdateDTO(Integer commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
