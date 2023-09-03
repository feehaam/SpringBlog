package com.feeham.blog.service.ServiceImpl;

import com.feeham.blog.DTO.UserCreateDTO;
import com.feeham.blog.DTO.UserReadDTO;
import com.feeham.blog.DTO.UserUpdateDTO;
import com.feeham.blog.entity.User;
import com.feeham.blog.repository.UserRepository;
import com.feeham.blog.service.IService.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        User user = modelMapper.map(userCreateDto, User.class);
        userRepository.save(user);
    }

    @Override
    public Optional<UserReadDTO> read(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            UserReadDTO userReadDTO = modelMapper.map(user, UserReadDTO.class);
            return Optional.of(userReadDTO);
        }
        else return Optional.empty();
    }

    @Override
    public void update(UserUpdateDTO userUpdateDto) {
        Optional<User> userOptional = userRepository.findById(userUpdateDto.getId());
        if (userOptional.isPresent()) {
            User user = modelMapper.map(userUpdateDto, User.class);
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
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserReadDTO.class))
                .collect(Collectors.toList());
    }
}
