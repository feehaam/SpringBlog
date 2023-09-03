package com.feeham.blog.service.IService;

import com.feeham.blog.DTO.UserCreateDTO;
import com.feeham.blog.DTO.UserReadDTO;
import com.feeham.blog.DTO.UserUpdateDTO;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    void create(UserCreateDTO userCreateDto);
    UserReadDTO read(Integer userId);
    void update(UserUpdateDTO userUpdateDto);
    void delete(Integer userId);
    List<UserReadDTO> readAll();
}
