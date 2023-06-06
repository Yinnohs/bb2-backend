package com.yinnohs.bb2.Example.application.controller;


import com.yinnohs.bb2.Example.application.mapper.interfaces.BaseMapper;
import com.yinnohs.bb2.Example.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private UserService userService;
    private BaseMapper mapper;

    @Autowired
    public AuthController( UserService userService,  BaseMapper mapper){
        this.mapper = mapper;
        this.userService = userService;
    }



//    @PostMapping("/local/login")
//    public ResponseEntity<Void> login
//
//    @PostMapping("/local/register")
//
//    @PostMapping("/logout")

}
