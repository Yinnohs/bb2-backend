package com.yinnohs.bb2.Example;

import com.yinnohs.bb2.Example.application.model.Role;
import com.yinnohs.bb2.Example.application.model.User;
import com.yinnohs.bb2.Example.application.repository.RoleRepository;
import com.yinnohs.bb2.Example.application.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Bb2ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(Bb2ExampleApplication.class, args);
	}
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder encoder){
		return  args -> {

			if (roleRepository.findByAuthority("ADMIN").isPresent()){
				return;
			}
			Role adminRole = roleRepository.save(new Role(Long.valueOf(1),"ADMIN"));
			roleRepository.save(new Role(Long.valueOf(2),"CLIENT"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);
			String encodedPassword = encoder.encode("1234");

			User admin  = new User(1,"sexy administrator", "really sexy", "admin@admin.com", LocalDate.now(),roles,encodedPassword,false,null);

			userRepository.save(admin);

		};
	}

	@Bean
	public WebMvcConfigurer corsConfiguration(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
			}
		};
	}
}
