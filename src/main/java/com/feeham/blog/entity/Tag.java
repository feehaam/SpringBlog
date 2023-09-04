package com.feeham.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // The name of the tag
    private String tag;

    // Relational references
    // Many-to-Many relationship with Post (posts associated with the tag)
    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Post> posts;

    // Default constructor
    public Tag() {
    }

    // Parameterized constructor
    public Tag(String tag) {
        this.tag = tag;
    }

    // Getter and Setter methods for class attributes
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
