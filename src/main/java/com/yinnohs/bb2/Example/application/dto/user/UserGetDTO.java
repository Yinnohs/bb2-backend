package com.yinnohs.bb2.Example.application.dto.user;

import com.yinnohs.bb2.Example.application.model.Role;
import com.yinnohs.bb2.Example.domain.models.interfaces.IUser;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserGetDTO implements IUser, Serializable {

    private  long userId;


    private  String name;


    private  String surname;


    private  String email;


    private LocalDate creationDate;

    private Collection<Role> authorities;

}
