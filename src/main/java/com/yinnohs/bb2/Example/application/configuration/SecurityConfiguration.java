package com.yinnohs.bb2.Example.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpContext) throws Exception{
        httpContext
                .cors((httpSecurityCorsConfigurer)->
                        httpSecurityCorsConfigurer.disable())
                .csrf((httpSecurityCsrfConfigurer)->
                        httpSecurityCsrfConfigurer.disable())

                .authorizeHttpRequests((auth) ->
                        auth
                                .anyRequest()
                                .permitAll()

                );
                return httpContext.build();
    }
}
