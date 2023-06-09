package com.yinnohs.bb2.Example.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class TokenService {


    private JwtEncoder jwtEncoder;

    private JwtDecoder jwtDecoder;

    @Autowired
    public TokenService(JwtEncoder jwtEncoder,JwtDecoder jwtDecoder){
        this.jwtDecoder = jwtDecoder;
        this.jwtEncoder = jwtEncoder;
    }


    public String generateJwt(Authentication auth){

        Instant now = Instant.now();
        Instant expireAt = now.plus(1, ChronoUnit.DAYS);

        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .subject(auth.getName())
                .claim("roles", scope)
                .expiresAt(expireAt)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
