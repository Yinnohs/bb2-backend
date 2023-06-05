package com.yinnohs.bb2.Example.application.controller;

import com.yinnohs.bb2.Example.application.dto.user.UserCreateDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserGetDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserUpdateDTO;
import com.yinnohs.bb2.Example.application.mapper.interfaces.BaseMapper;
import com.yinnohs.bb2.Example.application.model.User;
import com.yinnohs.bb2.Example.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {


    private UserService userService;

    BaseMapper mapper;

    @Autowired
    public  UserController(UserService userService, BaseMapper mapper){
        this.userService = userService;
        this.mapper = mapper;
    }

    public  UserController(){}

    @GetMapping("/all")
    public ResponseEntity<List<UserGetDTO>> getAllUsers(){
        try {

            List<User> users = this.userService.findAllUsers();
            List<UserGetDTO> response = new ArrayList<>();
            if (users.size() > 0){
                for(User user : users){
                    UserGetDTO userGetDTO = mapper.userToGetDTO(user);
                    response.add(userGetDTO);
                }

            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserGetDTO> GetOneUserById(@PathVariable("userId") long userId) {
        try {
            User user = this.userService.findUserById(userId);
            if (user == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            UserGetDTO response = mapper.userToGetDTO(user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/local/new")
    public ResponseEntity<UserGetDTO> createUser(@RequestBody() @Validated UserCreateDTO payload){

        try {

            User newUser = this.mapper.userCreateDTOTouser(payload);
            User createdUser = this.userService.createUser(newUser);
            UserGetDTO response = this.mapper.userToGetDTO(createdUser);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserGetDTO> updateUser(@RequestBody() @Validated UserUpdateDTO payload){
        try {
            if (payload == null){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            User updatedUser = this.userService.updateUser(payload);

            UserGetDTO response = this.mapper.userToGetDTO(updatedUser);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
