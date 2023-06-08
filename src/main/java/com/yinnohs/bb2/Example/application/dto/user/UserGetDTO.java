package com.yinnohs.bb2.Example.application.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yinnohs.bb2.Example.application.dto.role.RoleGetDTO;
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

    @JsonProperty("user_id")
    private  long userId;

    @JsonProperty("name")
    private  String name;

    @JsonProperty("surname")
    private  String surname;

    @JsonProperty("email")
    private  String email;

    @JsonProperty("creation_date")
    private LocalDate creationDate;

    @JsonProperty("roles")
    private Collection<RoleGetDTO> authorities;

}
