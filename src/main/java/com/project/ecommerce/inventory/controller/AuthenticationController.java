package com.project.ecommerce.inventory.controller;

import com.project.ecommerce.inventory.dto.AuthenticationDataDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AuthenticationController {

    @Autowired
    AuthenticationManager manager;

    @PostMapping
    public ResponseEntity<AuthenticationDataDto> login (@RequestBody @Valid AuthenticationDataDto dto){

        var token = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}
