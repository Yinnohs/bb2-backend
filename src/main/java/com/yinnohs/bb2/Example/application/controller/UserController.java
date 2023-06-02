package com.yinnohs.bb2.Example.application.controller;

import com.yinnohs.bb2.Example.application.dto.user.UserGetDTO;
import com.yinnohs.bb2.Example.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, UserGetDTO>> GetOneUserById(@PathVariable() long userId){
        try {

            return new ResponseEntity<>(null, HttpStatus.OK);


        }catch(Exception e) {

            Map<String, UserGetDTO> response = new HashMap<String, UserGetDTO>(){{put("data", null);}};

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
