package com.swapiffy.swapiffybe.dto.converter;

import com.swapiffy.swapiffybe.dto.UserRegisterRequest;
import com.swapiffy.swapiffybe.dto.UserRegistrationDTO;
import com.swapiffy.swapiffybe.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User convertToEntity(UserRegistrationDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        return user;
    }
}
