package com.feeham.blog.service.ServiceImpl;

import com.feeham.blog.DTO.PostReadDTO;
import com.feeham.blog.DTO.UserCreateDTO;
import com.feeham.blog.DTO.UserReadDTO;
import com.feeham.blog.DTO.UserUpdateDTO;
import com.feeham.blog.entity.Post;
import com.feeham.blog.entity.Tag;
import com.feeham.blog.entity.User;
import com.feeham.blog.repository.UserRepository;
import com.feeham.blog.service.IService.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){

            User user = userOptional.get();
            UserReadDTO ud = modelMapper.map(user, UserReadDTO.class);
            return Optional.of(ud);
//            UserReadDTO userDto = new UserReadDTO();
//
//            userDto.setAge(user.getAge());
//            userDto.setBio(user.getBio());
//            userDto.setEmail(user.getEmail());
//            userDto.setId(user.getId());
//            userDto.setDateJoined(user.getDateJoined());
//            userDto.setDateOfBirth(user.getDateOfBirth());
//            userDto.setFirstName(user.getFirstName());
//            userDto.setLastName(user.getLastName());
//            userDto.setProfileImageUrl(user.getProfileImageUrl());
//
//            userDto.setPosts(new ArrayList<>());
//            PostReadDTO postDto = new PostReadDTO();
//            for(Post post: user.getPosts()){
//                postDto.setTitle(post.getTitle());
//                postDto.setContent(post.getContent());
//                postDto.setTimeCreated(post.getTimeCreated());
//                postDto.setTimeLastModified(post.getTimeLastModified());
//                postDto.setComments(post.getComments());
//                postDto.setTags(post.getTags());
//                postDto.setUserID(user.getId());
//                postDto.setUserFullName(user.getFirstName() + " " + user.getLastName());
//
//                userDto.getPosts().add(postDto);
//            }

            //return Optional.of(userDto);
        }
        return null;
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
