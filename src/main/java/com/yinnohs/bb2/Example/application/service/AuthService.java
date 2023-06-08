package com.yinnohs.bb2.Example.application.service;

import com.yinnohs.bb2.Example.application.dto.auth.UserLoginDTO;
import com.yinnohs.bb2.Example.application.dto.auth.UserLoginResponseDTO;
import com.yinnohs.bb2.Example.application.dto.user.UserGetDTO;
import com.yinnohs.bb2.Example.application.mapper.interfaces.BaseMapper;
import com.yinnohs.bb2.Example.application.model.Role;
import com.yinnohs.bb2.Example.application.model.User;
import com.yinnohs.bb2.Example.application.repository.RoleRepository;
import com.yinnohs.bb2.Example.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

@Service
@Transactional
public class AuthService {


    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;
    private AuthenticationManager authManager;
    private TokenService tokenService;
    private BaseMapper mapper;

    @Autowired
    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, AuthenticationManager authManager, TokenService tokenService, BaseMapper mapper){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.authManager = authManager;
        this.tokenService = tokenService;
        this.mapper = mapper;
    }

    public User registerLocalUser(User user){

        String hashedPassword = encoder.encode(user.getPassword());

        Role clientRole = this.roleRepository.findById(2l).orElse(null);
        if(clientRole == null){
            return  null;
        }

        Collection<Role> roles = new HashSet<>();
        roles.add(clientRole);

        user.setPassword(hashedPassword);
        user.setAuthorities(roles);
        user.setDeleted(false);
        user.setCreationDate(LocalDate.now());

        return this.userRepository.save(user);
    }

    public UserLoginResponseDTO localLogin(UserLoginDTO userLoginDTO){
        UserLoginResponseDTO data = new UserLoginResponseDTO(null, "");

        try{

            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword())
            );

            String token = tokenService.generateJwt(auth);

            User currentUser = this.userRepository.findUserByEmail(userLoginDTO.getEmail()).orElse(null);


            if (currentUser != null){
                data.setJwt(token);
                UserGetDTO userGetDTO = this.mapper.userToGetDTO(currentUser);
                data.setUser(userGetDTO);
            }

            return data;

        }catch(Exception e){

            return data;
        }

    }

}
