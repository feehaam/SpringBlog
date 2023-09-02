package com.feeham.blog.entity;

import com.mysql.cj.protocol.ColumnDefinition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime timeCreated;
    private LocalDateTime timeLastModified;

    // Relational references
    @ManyToOne
    @JoinColumn(name = "author_uid")
    private User author;
    @OneToMany(mappedBy = "parentPost", cascade = CascadeType.ALL)
    private List<Comment> comments;
    @ManyToMany
    @JoinTable(
            name = "posts_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;
}
