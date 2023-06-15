package com.yinnohs.bb2.Example.application.dto.user;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserUpdateDTO {

    @JsonProperty("user_id")
    private  long userId;

    private  String name;

    private  String surname;

    private  String email;

    private String password;

}
