package com.yinnohs.bb2.Example.application.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yinnohs.bb2.Example.domain.models.interfaces.IUser;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

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

}
