package com.feeham.blog.DTO;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

// Comment data transfer object for updating a User
@Component
public class UserUpdateDTO {
    private Integer id;
    private String password;
    private String firstName;
    private String lastName;
    private Integer age;
    private LocalDate dateOfBirth;
    private String bio;
    private String profileImageUrl;

    public UserUpdateDTO() {

    }

    public UserUpdateDTO(Integer id, String password, String firstName, String lastName, Integer age,
                         LocalDate dateOfBirth, String bio, String profileImageUrl) {
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.profileImageUrl = profileImageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
