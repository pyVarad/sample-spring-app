package com.app.aeportal.Services;

import com.app.aeportal.dto.request.UserRequestDto;
import com.app.aeportal.dto.response.UserResponseDto;

public interface UserService {

    UserResponseDto[] getUsers();
    UserResponseDto getUserById(Long id);
    UserResponseDto addNewUser(UserRequestDto request);
    UserResponseDto updateUserInfo(Long id, UserRequestDto request);
    void deleteUserInfo(Long id);
}
