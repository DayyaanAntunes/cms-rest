package com.challenge.cms.auth.presentation;

import com.challenge.cms.auth.domain.command.AuthCommand;
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
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping
    public ResponseEntity<Void> login(@RequestBody @Valid AuthCommand authCommand){
        var emailPassword = new UsernamePasswordAuthenticationToken(authCommand.email(), authCommand.password());
        this.authenticationManager.authenticate(emailPassword);

        return ResponseEntity.ok().build();
    }
}