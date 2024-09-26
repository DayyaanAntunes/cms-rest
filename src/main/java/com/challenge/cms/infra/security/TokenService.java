package com.challenge.cms.infra.security;

import com.challenge.cms.user.domain.model.User;

public interface TokenService {
    String generateToken(User user);
    String validateToken(String token);
}