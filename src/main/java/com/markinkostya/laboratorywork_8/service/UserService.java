package com.markinkostya.laboratorywork_8.service;

import com.markinkostya.laboratorywork_8.dto.UserDto;
import com.markinkostya.laboratorywork_8.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
