package com.yinnohs.bb2.Example.application.mapper.interfaces;

import com.yinnohs.bb2.Example.application.dto.user.UserCreateDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserGetDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserUpdateDTO;
import com.yinnohs.bb2.Example.application.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaseMapper {

    UserGetDTO userToGetDTO (User user);
    User userCreateDTOTouser(UserCreateDTO userCreateDTO);

}
