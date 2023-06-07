package com.yinnohs.bb2.Example.application.service;

import com.yinnohs.bb2.Example.application.dto.user.UserUpdateDTO;
import com.yinnohs.bb2.Example.application.model.User;
import com.yinnohs.bb2.Example.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public List<User> findAllUsers(){
        List<User> users = this.repository.findAll();

        return users;
    }

    public User findUserById(long id){
        Optional<User> user =  this.repository.findById(id);
        return user.orElse(null);

    }

    public CompletableFuture<User> findUserByIdFuture(Long userId){
        if (userId == null){
            return null;
        }

        CompletableFuture<User> futureData = CompletableFuture.supplyAsync(()->{
            return this.repository.findById(userId).orElse(null);
        });

        return futureData;
    }

    public User createUser (User user){

        String password = encoder.encode(user.getPassword());

        user.setPassword(password);

        user.setDeleted(false);

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

        user.setDeleted(true);

        return this.repository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return this.repository.findUserByEmail(username).orElseThrow(()-> new  UsernameNotFoundException("wrong credentials"));
    }
}
