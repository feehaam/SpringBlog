package com.feeham.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime timeCreated;
    private LocalDateTime timeLastModified;

    // Relational references
    @ManyToOne
    @JoinColumn(name = "author_uid")
    @JsonIgnore
    private User author;
    @OneToMany(mappedBy = "parentPost", cascade = CascadeType.ALL)
    private List<Comment> comments;
    @ManyToMany
    @JoinTable(
            name = "posts_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonIgnore
    private List<Tag> tags;

    public Post() {

    }

    public Post(Integer id, String title, String content, LocalDateTime timeCreated,
                LocalDateTime timeLastModified, User author, List<Comment> comments, List<Tag> tags) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.timeCreated = timeCreated;
        this.timeLastModified = timeLastModified;
        this.author = author;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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
