package com.challenge.cms.auth.presentation;

import com.challenge.cms.auth.domain.command.AuthCommand;
import com.challenge.cms.infra.security.TokenServiceImpl;
import com.challenge.cms.user.domain.model.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private TokenServiceImpl tokenServiceImpl;

    @PostMapping
    public ResponseEntity<AuthJson> login(@RequestBody @Valid AuthCommand authCommand){
        var emailPassword = new UsernamePasswordAuthenticationToken(authCommand.email(), authCommand.password());
        var auth = this.authenticationManager.authenticate(emailPassword);
        var token = tokenServiceImpl.generateToken((User)auth.getPrincipal());

        return ResponseEntity.ok().body(new AuthJson(token));
    }
}