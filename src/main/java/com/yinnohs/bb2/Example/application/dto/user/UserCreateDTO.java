package com.yinnohs.bb2.Example.application.dto.user;
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
public class UserCreateDTO {

    private  String name;

    private  String surname;

    private  String email;

    private String password;


}
