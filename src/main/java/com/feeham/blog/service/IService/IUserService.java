package com.feeham.blog.service.IService;

import com.feeham.blog.DTO.UserCreateDto;
import com.feeham.blog.DTO.UserUpdateDto;
import com.feeham.blog.entity.User;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    void create(UserCreateDto userCreateDto);
    Optional<User> read(Integer userId);
    void update(UserUpdateDto userUpdateDto);
    void delete(Integer userId);
    List<User> readAll();
}
