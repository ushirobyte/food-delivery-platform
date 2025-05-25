package com.ushirobyte.food.auth_service.service;

import com.ushirobyte.food.auth_service.dto.AuthResponse;
import com.ushirobyte.food.auth_service.dto.LoginRequest;
import com.ushirobyte.food.auth_service.dto.RegisterRequest;

public interface AuthService {

    public void register(RegisterRequest registerRequest);

    public AuthResponse login(LoginRequest loginRequest);
    String refreshAccessToken(String refreshToken);

}
