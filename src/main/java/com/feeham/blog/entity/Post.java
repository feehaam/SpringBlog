package com.feeham.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {
    // Post id - primary key
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // Other properties
    private String title;
    // The content of the post, defined as TEXT for better storage
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime timeCreated;
    private LocalDateTime timeLastModified;

    // Relational references
    // Many-to-One relationship with User (author of the post)
    @ManyToOne
    @JoinColumn(name = "author_uid")
    @JsonIgnore
    private User author;
    // One-to-Many relationship with Comment (comments on the post)
    @OneToMany(mappedBy = "parentPost", cascade = CascadeType.ALL)
    private List<Comment> comments;
    // Many-to-Many relationship with Tag (tags associated with the post)
    @ManyToMany
    @JoinTable(
            name = "posts_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonIgnore
    private List<Tag> tags;

    // Default constructor
    public Post() {
    }

    // Parameterized constructor
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

    // Getter and Setter methods for class attributes
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
