package com.challenge.cms.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.challenge.cms.configuration.exception.ResponseException;
import com.challenge.cms.user.domain.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class TokenServiceImpl implements TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    @Override
    public String generateToken(User user) {
        return null;
    }

    @Override
    public String validateToken(String token) {
        return null;
    }
}
