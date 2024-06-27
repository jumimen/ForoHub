package com.example.ForoHub.controller;

import com.example.ForoHub.domain.user.DataAuthenticationUser;
import com.example.ForoHub.domain.user.User;
import com.example.ForoHub.infra.security.DataJWTToken;
import com.example.ForoHub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity userAuthentication(@RequestBody @Valid DataAuthenticationUser dataAuthenticationUser){
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(dataAuthenticationUser.login(), dataAuthenticationUser.password());
        var userAuthenticated =  authenticationManager.authenticate(authenticationToken);
        var jwtToken = tokenService.toGenerateToken((User)userAuthenticated.getPrincipal());
        return  ResponseEntity.ok(new DataJWTToken(jwtToken));
    }
        
}
