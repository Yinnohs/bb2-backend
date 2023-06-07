package com.yinnohs.bb2.Example.application.service;

import com.yinnohs.bb2.Example.application.dto.user.UserUpdateDTO;
import com.yinnohs.bb2.Example.application.model.Role;
import com.yinnohs.bb2.Example.application.model.User;
import com.yinnohs.bb2.Example.application.repository.RoleRepository;
import com.yinnohs.bb2.Example.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(PasswordEncoder encoder, UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    public List<User> findAllUsers(){
        List<User> users = this.userRepository.findAll();

        return users;
    }

    public User findUserById(long id){
        Optional<User> user =  this.userRepository.findById(id);
        return user.orElse(null);

    }

    public CompletableFuture<User> findUserByIdFuture(Long userId){
        if (userId == null){
            return null;
        }

        CompletableFuture<User> futureData = CompletableFuture.supplyAsync(()->{
            return this.userRepository.findById(userId).orElse(null);
        });

        return futureData;
    }

    public User createUser (User user){

        String password = encoder.encode(user.getPassword());

        user.setPassword(password);

        user.setDeleted(false);

        LocalDate currentDate = LocalDate.now();

        user.setCreationDate(currentDate);
        
        return this.userRepository.save(user);
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

        return this.userRepository.save(user);
    }

    public  User delegateUserToClient(Long userId){
        if (userId == null){
            return null;
        }

        User user = userRepository.findById(userId).orElse(null);

        if (user == null){
            throw new UsernameNotFoundException("this user does not exist");
        }

        Role clientRole = roleRepository.findByAuthority("CLIENT").orElse(null);
        Collection<Role> roles = new HashSet<>();

        roles.add(clientRole);

        user.setAuthorities(roles);

        return this.userRepository.save(user);
    }

    public  User delegateUserToAdmin(Long userId){
        if (userId == null){
            return null;
        }

        User user = userRepository.findById(userId).orElse(null);

        if (user == null){
            throw new UsernameNotFoundException("this user does not exist");
        }

        Role clientRole = roleRepository.findByAuthority("ADMIN").orElse(null);
        Collection<Role> roles = new HashSet<>();

        roles.add(clientRole);

        user.setAuthorities(roles);

        return this.userRepository.save(user);
    }

    public User softDeleteUser (long userId){

        User user = this.findUserById(userId);

        user.setDeleted(true);

        return this.userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return this.userRepository.findUserByEmail(username).orElseThrow(()-> new  UsernameNotFoundException("wrong credentials"));
    }
}
