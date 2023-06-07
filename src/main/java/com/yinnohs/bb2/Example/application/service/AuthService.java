package com.yinnohs.bb2.Example.application.service;

import com.yinnohs.bb2.Example.application.dto.auth.UserLoginDTO;
import com.yinnohs.bb2.Example.application.model.Role;
import com.yinnohs.bb2.Example.application.model.User;
import com.yinnohs.bb2.Example.application.repository.RoleRepository;
import com.yinnohs.bb2.Example.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service
@Transactional
public class AuthService {


    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private PasswordEncoder encoder;

    @Autowired
    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public User registerLocalUser(User user){

        String hashedPassword = encoder.encode(user.getPassword());

        Role clientRole = this.roleRepository.findByAuthority("CLIENT").orElse(null);
        if(clientRole == null){
            return  null;
        }

        Collection<Role> roles = new HashSet<>();
        roles.add(clientRole);

        user.setPassword(hashedPassword);
        user.setAuthorities(roles);


        return this.userRepository.save(user);

    }

    public User localLogin(UserLoginDTO userLoginDTO){

        if(userLoginDTO == null){
            return null;
        }

        Optional<User> user = this.userRepository.findUserByEmail(userLoginDTO.getEmail());
        String encodedPassword = this.encoder.encode(userLoginDTO.getPassword());

        if (!user.isPresent()){
            return null;
        }

        User currentUser = user.get();

        if (user.isPresent() && !encodedPassword.equals(currentUser.getPassword())) {
            return null;
        }

        return currentUser;

    }

}
