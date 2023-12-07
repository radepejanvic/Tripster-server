package com.tripster.project.mapper;

import com.tripster.project.dto.UserDTO;
import com.tripster.project.model.User;

public class UserDTOMapper {

    public static UserDTO fromUsertoUserDTO(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }
    public static User fromUserDTOtoUser(UserDTO userDTO){
        User user = new User();

        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());

        return user;
    }
}
