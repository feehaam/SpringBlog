package com.feeham.blog.service.ServiceImpl;

import com.feeham.blog.DTO.UserCreateDTO;
import com.feeham.blog.DTO.UserReadDTO;
import com.feeham.blog.DTO.UserUpdateDTO;
import com.feeham.blog.entity.User;
import com.feeham.blog.repository.UserRepository;
import com.feeham.blog.service.IService.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(UserCreateDTO userCreateDto) {
        User user = new User(0, userCreateDto.getEmail(), userCreateDto.getPassword(),
                userCreateDto.getFirstName(), userCreateDto.getLastName(), userCreateDto.getAge(),
                userCreateDto.getDateOfBirth(), userCreateDto.getBio(), userCreateDto.getProfileImageUrl(),
                LocalDate.now(), new ArrayList<>(), new ArrayList<>());
        userRepository.save(user);
    }

    @Override
    public Optional<UserReadDTO> read(Integer userId) {
        Optional<User> postOptional = userRepository.findById(userId);
        return postOptional.map(post -> modelMapper.map(post, UserReadDTO.class));
    }

    @Override
    public void update(UserUpdateDTO userUpdateDto) {
        Optional<User> userOptional = userRepository.findById(userUpdateDto.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            modelMapper.map(userUpdateDto, user);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userUpdateDto.getId());
        }
    }

    @Override
    public void delete(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserReadDTO> readAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserReadDTO.class))
                .collect(Collectors.toList());
    }
}
