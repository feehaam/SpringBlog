package com.feeham.blog.service.ServiceImpl;

import com.feeham.blog.DTO.UserCreateDTO;
import com.feeham.blog.DTO.UserReadDTO;
import com.feeham.blog.DTO.UserUpdateDTO;
import com.feeham.blog.entity.User;
import com.feeham.blog.exceptions.NoRecordException;
import com.feeham.blog.exceptions.ResourceNotFoundException;
import com.feeham.blog.repository.UserRepository;
import com.feeham.blog.service.IService.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    // Constructor injection of repositories and services
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Create a new user.
     * @param userCreateDto The DTO containing user data to create.
     */
    @Override
    public void create(UserCreateDTO userCreateDto) {
        User user = modelMapper.map(userCreateDto, User.class);
        user.setDateJoined(LocalDate.now());
        userRepository.save(user);
    }

    /**
     * Read a user by their ID.
     * @param userId The ID of the user to read.
     * @return The read UserReadDTO.
     * @throws ResourceNotFoundException if the user does not exist.
     */
    @Override
    public UserReadDTO read(Integer userId) throws ResourceNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return modelMapper.map(user, UserReadDTO.class);
        } else {
            throw new ResourceNotFoundException("User not found", "Read user", "User not found with ID: " + userId);
        }
    }

    /**
     * Update user information.
     * @param userUpdateDTO The DTO containing updated user data.
     * @throws ResourceNotFoundException if the user does not exist.
     */
    @Override
    public void update(UserUpdateDTO userUpdateDTO) throws ResourceNotFoundException {
        Optional<User> userOptional = userRepository.findById(userUpdateDTO.getId());
        if (userOptional.isPresent()) {
            User user = modelMapper.map(userUpdateDTO, User.class);
            userRepository.save(user);
        } else {
            throw new ResourceNotFoundException("User not found", "Update user", "User not found with ID: " + userUpdateDTO.getId());
        }
    }

    /**
     * Delete a user by their ID.
     * @param userId The ID of the user to delete.
     * @throws ResourceNotFoundException if the user does not exist.
     */
    @Override
    public void delete(Integer userId) throws ResourceNotFoundException {
        read(userId);
        userRepository.deleteById(userId);
    }

    /**
     * Read all users.
     * @return A list of UserReadDTOs.
     * @throws NoRecordException if no users are found.
     */
    @Override
    public List<UserReadDTO> readAll() throws NoRecordException {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()){
            throw new NoRecordException("No users registered", "List of user", "No records of users exist in the database.");
        }
        return users.stream()
                .map(user -> modelMapper.map(user, UserReadDTO.class))
                .collect(Collectors.toList());
    }
}
