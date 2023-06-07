package com.yinnohs.bb2.Example.application.controller;


import com.yinnohs.bb2.Example.application.dto.user.UserCreateDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserGetDTO;
import com.yinnohs.bb2.Example.application.mapper.interfaces.BaseMapper;
import com.yinnohs.bb2.Example.application.model.User;
import com.yinnohs.bb2.Example.application.service.AuthService;
import com.yinnohs.bb2.Example.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin()
public class AuthController {

    private AuthService authService;
    private BaseMapper mapper;

    @Autowired
    public AuthController( AuthService authService,  BaseMapper mapper){
        this.mapper = mapper;
        this.authService = authService;
    }



//    @PostMapping("/local/login")
//    public ResponseEntity<Void> login
//
    @PostMapping("/local/register")
    public ResponseEntity<UserGetDTO> registerLocal(@RequestBody() UserCreateDTO payload){

        try {

            User newUser = this.mapper.userCreateDTOTouser(payload);
            User createdUser = this.authService.registerLocalUser(newUser);
            UserGetDTO response = this.mapper.userToGetDTO(createdUser);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//
//    @PostMapping("/logout")

}
