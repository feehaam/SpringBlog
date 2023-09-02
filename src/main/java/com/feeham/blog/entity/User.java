package com.feeham.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private LocalDate dateOfBirth;
    private String bio;
    private String profileImageUrl;
    private LocalDate dateJoined;

    // Relationships
    @OneToMany(mappedBy = "author")
    private List<Post> posts; // Posts authored by the user

    @OneToMany(mappedBy = "user")
    private List<Comment> comments; // Comments made by the user
}
