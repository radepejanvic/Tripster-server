package com.tripster.project.modelmapper;

import com.tripster.project.dto.UserDTO;
import com.tripster.project.dto.UserLoginDTO;
import com.tripster.project.model.User;

public class UserDTOMapper {

    public static UserDTO fromUsertoUserDTO(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setUserType(user.getUserType());
        userDTO.setId(user.getId());
        userDTO.setUserStatus(user.getStatus());

        return userDTO;
    }
}
