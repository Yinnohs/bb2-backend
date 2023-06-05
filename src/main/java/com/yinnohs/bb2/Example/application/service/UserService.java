package com.yinnohs.bb2.Example.application.service;

import com.yinnohs.bb2.Example.application.dto.user.UserUpdateDTO;
import com.yinnohs.bb2.Example.application.model.User;
import com.yinnohs.bb2.Example.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final int bcryptSalts = 10;

    @Autowired
    private UserRepository repository;

    public List<User> findAllUsers(){
        List<User> users = this.repository.findAll();

        return users;
    }

    public User findUserById(long id){
        Optional<User> user =  this.repository.findById(id);
        return user.orElse(null);

    }

    public User createUser (User user){

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(bcryptSalts);

        String password = passwordEncoder.encode(user.getPassword());

        user.setPassword(password);

        LocalDate currentDate = LocalDate.now();

        user.setCreationDate(currentDate);
        
        return this.repository.save(user);
    }

    public User updateUser (UserUpdateDTO userData){

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

        User user = this.findUserById(userId);

        user.setIsDeleted(true);

        return this.repository.save(user);

    }
}
