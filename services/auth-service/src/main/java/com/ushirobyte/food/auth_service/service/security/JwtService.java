package com.ushirobyte.food.auth_service.service.security;

public interface JwtService {

    public String generateToken(String email);

}
