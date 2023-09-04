package com.feeham.blog.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feeham.blog.entity.Comment;
import com.feeham.blog.entity.Tag;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

// Comment data transfer object for Reading a Post
@Component
public class PostReadDTO {
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

    public PostReadDTO(){

    }

    public PostReadDTO(Integer id, String title, String content, LocalDateTime timeCreated,
                       LocalDateTime timeLastModified, Integer userID, String userFullName,
                       String userEmail, List<Comment> comments, List<Tag> tags) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.timeCreated = timeCreated;
        this.timeLastModified = timeLastModified;
        this.userID = userID;
        this.userFullName = userFullName;
        this.userEmail = userEmail;
        this.comments = comments;
        this.tags = tags;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public LocalDateTime getTimeLastModified() {
        return timeLastModified;
    }

    public void setTimeLastModified(LocalDateTime timeLastModified) {
        this.timeLastModified = timeLastModified;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
