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

    public CommentReadDTO() {

    }

    public CommentReadDTO(Integer id, String content, LocalDateTime timeCreated, Integer upVotes,
                          Integer downVotes, Integer userID, String userFullName, String userEmail,
                          Integer parentPostId, Integer parentCommentId, List<Comment> replies) {
        this.id = id;
        this.content = content;
        this.timeCreated = timeCreated;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.userID = userID;
        this.userFullName = userFullName;
        this.userEmail = userEmail;
        this.parentPostId = parentPostId;
        this.parentCommentId = parentCommentId;
        this.replies = replies;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Integer getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(Integer upVotes) {
        this.upVotes = upVotes;
    }

    public Integer getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(Integer downVotes) {
        this.downVotes = downVotes;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getParentPostId() {
        return parentPostId;
    }

    public void setParentPostId(Integer parentPostId) {
        this.parentPostId = parentPostId;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }
}