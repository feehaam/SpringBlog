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
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        userService.create(userCreateDTO);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Integer userId) throws ResourceNotFoundException {
        UserReadDTO user = userService.read(userId);
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Integer userId, @RequestBody UserUpdateDTO userUpdateDTO)
                throws ResourceNotFoundException{
        userUpdateDTO.setId(userId);
        userService.update(userUpdateDTO);
        return new ResponseEntity<>("User updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) throws ResourceNotFoundException {
        userService.delete(userId);
        return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() throws NoRecordException {
        List<UserReadDTO> users = userService.readAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
