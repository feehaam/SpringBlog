package com.feeham.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment {

    // Primary key for the table
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Defined as TEXT column so that it doesn't be defined as varchar by default
    // As varchar has limited functionality and data side (up to 255)
    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime timeCreated;
    private Integer upVotes;
    private Integer downVotes;

    // Relational references
    // User: parent reference, JsonIgnored to avoid circular references
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    // Post: parent reference, JsonIgnored to avoid circular references
    @ManyToOne
    @JoinColumn(name = "parent_post_id")
    @JsonIgnore
    private Post parentPost;

    /**
     * - Recursive self referencing relation
     * A comment can have multiple replies (in this case Comment class itself)
     * The reply can have multiple replies... and so on
     * -
     * parentComment refers to the parent comment if the comment is a reply
     * replies is the list of replies in current comment
     */
    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    @JsonIgnore
    private Comment parentComment;
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replies;

    // Constructors and Getters-Setters
    public Comment() {

    }

    public Comment(Integer id, String content, LocalDateTime timeCreated, Integer upVotes,
                   Integer downVotes, User user, Post parentPost,
                   Comment parentComment, List<Comment> replies) {
        this.id = id;
        this.content = content;
        this.timeCreated = timeCreated;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.user = user;
        this.parentPost = parentPost;
        this.parentComment = parentComment;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getParentPost() {
        return parentPost;
    }

    public void setParentPost(Post parentPost) {
        this.parentPost = parentPost;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }
}
