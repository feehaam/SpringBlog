package com.feeham.blog.DTO;

import com.feeham.blog.entity.Comment;
import com.feeham.blog.entity.Post;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class UserReadDto {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private Integer age;
    private LocalDate dateOfBirth;
    private String bio;
    private String profileImageUrl;
    private LocalDate dateJoined;
    private List<Post> posts;
    private List<Comment> comments;
}
