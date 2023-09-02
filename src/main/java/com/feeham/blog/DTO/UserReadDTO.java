package com.feeham.blog.DTO;

import com.feeham.blog.entity.Comment;
import com.feeham.blog.entity.Post;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class UserReadDTO {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private Integer age;
    private LocalDate dateOfBirth;
    private String bio;
    private String profileImageUrl;
    private LocalDate dateJoined;
    private List<PostReadDTO> posts;
    private List<Comment> comments;

    public UserReadDTO(){

    }

    public UserReadDTO(Integer id, String email, String firstName, String lastName,
                       Integer age, LocalDate dateOfBirth, String bio, String profileImageUrl,
                       LocalDate dateJoined, List<PostReadDTO> posts, List<Comment> comments) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.profileImageUrl = profileImageUrl;
        this.dateJoined = dateJoined;
        this.posts = posts;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

    public List<PostReadDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostReadDTO> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
