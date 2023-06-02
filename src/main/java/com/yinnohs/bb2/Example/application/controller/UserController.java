package com.yinnohs.bb2.Example.application.controller;

import com.yinnohs.bb2.Example.application.dto.user.UserCreateDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserGetDTO;
import com.yinnohs.bb2.Example.application.mapper.interfaces.BaseMapper;
import com.yinnohs.bb2.Example.application.model.User;
import com.yinnohs.bb2.Example.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    BaseMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<User> GetOneUserById(@PathVariable() long userId) {
        try {

            User user = this.userService.findUserById(userId);
            if (user == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            UserGetDTO response = mapper.userToGetDTO(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {


            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/local/creation")
    public ResponseEntity<UserGetDTO> createUserNoHash(@RequestBody() @Validated UserCreateDTO payload){

        try {

            User newUser = this.mapper.userCreateDTOTouser(payload);
            User createdUser = this.userService.createUser(newUser);
            UserGetDTO response = this.mapper.userToGetDTO(createdUser);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e) {


            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
