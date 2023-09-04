package com.feeham.blog.service.IService;

import com.feeham.blog.DTO.UserCreateDTO;
import com.feeham.blog.DTO.UserReadDTO;
import com.feeham.blog.DTO.UserUpdateDTO;

import java.util.List;

public interface IUserService {

    /**
     * User Service:
     * -
     * The following methods are responsible for user-related operations, including creating,
     * reading, updating, and deleting user records, as well as retrieving a list of all users.
     *  -
     * Note: Optional is not used as the null-pointer exception is checked in the service layer
     * and then thrown as custom exceptions, which are handled by a global exception handler.
     */

    void create(UserCreateDTO userCreateDto);
    UserReadDTO read(Integer userId);
    void update(UserUpdateDTO userUpdateDto);
    void delete(Integer userId);
    List<UserReadDTO> readAll();
}
