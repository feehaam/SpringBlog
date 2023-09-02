package com.feeham.blog.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component
@AllArgsConstructor
public class UserCreateDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Integer age;
    private LocalDate dateOfBirth;
    @Column(columnDefinition = "TEXT")
    private String bio;
    private String profileImageUrl;
}
