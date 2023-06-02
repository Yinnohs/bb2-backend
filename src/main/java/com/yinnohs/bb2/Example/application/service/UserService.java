package com.yinnohs.bb2.Example.application.service;

import com.yinnohs.bb2.Example.application.dto.user.UserGetDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserUpdateDTO;
import com.yinnohs.bb2.Example.application.model.User;
import com.yinnohs.bb2.Example.application.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository repository;

    public User findUserById(long id){
        Optional<User> user =  this.repository.findById(id);
        return user.orElse(null);

    }

    public User createUser (User user){
        return this.repository.save(user);
    }

    public User UpdateUser (UserUpdateDTO userData){

        User user = this.findUserById(userData.getUserId());

        if (userData.getName() != null){
            user.setName(userData.getName());
        }

        if (userData.getEmail() != null){
            user.setEmail(user.getEmail());
        }

        if (userData.getSurname() != null){
            user.setSurname(user.getSurname());
        }

        return this.repository.save(user);
    }

    public User softDeleteUser (long userId){
       return  this.repository.updateUserIsDelete(userId);
    }
}
