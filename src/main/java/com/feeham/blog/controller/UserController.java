package com.feeham.blog.controller;

import com.feeham.blog.DTO.UserCreateDTO;
import com.feeham.blog.DTO.UserReadDTO;
import com.feeham.blog.DTO.UserUpdateDTO;
import com.feeham.blog.exceptions.NoRecordException;
import com.feeham.blog.exceptions.ResourceNotFoundException;
import com.feeham.blog.service.IService.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    // Constructor injection of services
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * Create a new user.
     * @param userCreateDTO The DTO containing user data to create.
     * @return A ResponseEntity with a message and HTTP status code.
     */
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        userService.create(userCreateDTO);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    /**
     * Get a user by their ID.
     * @param userId The ID of the user to retrieve.
     * @return A ResponseEntity with the retrieved user and HTTP status code.
     * @throws ResourceNotFoundException if the user does not exist.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Integer userId) throws ResourceNotFoundException {
        UserReadDTO user = userService.read(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Update a user's information.
     * @param userId The ID of the user to update.
     * @param userUpdateDTO The DTO containing updated user data.
     * @return A ResponseEntity with a message and HTTP status code.
     * @throws ResourceNotFoundException if the user does not exist.
     */
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Integer userId, @RequestBody UserUpdateDTO userUpdateDTO)
            throws ResourceNotFoundException {
        userUpdateDTO.setId(userId);
        userService.update(userUpdateDTO);
        return new ResponseEntity<>("User updated successfully.", HttpStatus.OK);
    }

    /**
     * Delete a user by their ID.
     * @param userId The ID of the user to delete.
     * @return A ResponseEntity with a message and HTTP status code.
     * @throws ResourceNotFoundException if the user does not exist.
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) throws ResourceNotFoundException {
        userService.delete(userId);
        return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
    }

    /**
     * Get a list of all users.
     * @return A ResponseEntity with the list of users and HTTP status code.
     * @throws NoRecordException if no users are found.
     */
    @GetMapping
    public ResponseEntity<?> getAllUsers() throws NoRecordException {
        List<UserReadDTO> users = userService.readAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}