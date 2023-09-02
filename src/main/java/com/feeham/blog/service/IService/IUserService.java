package com.feeham.blog.service.IService;

import com.feeham.blog.DTO.UserCreateDto;
import com.feeham.blog.DTO.UserReadDto;
import com.feeham.blog.DTO.UserUpdateDto;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    void create(UserCreateDto userCreateDto);
    Optional<UserReadDto> read(Integer userId);
    void update(UserUpdateDto userUpdateDto);
    void delete(Integer userId);
    List<UserReadDto> readAll();
}
