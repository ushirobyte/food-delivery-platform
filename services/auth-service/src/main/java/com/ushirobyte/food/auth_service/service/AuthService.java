package com.ushirobyte.food.auth_service.service;

import com.ushirobyte.food.auth_service.dto.LoginRequest;
import com.ushirobyte.food.auth_service.dto.RegisterRequest;

public interface AuthService {

    public void register(RegisterRequest registerRequest);

    public String login(LoginRequest loginRequest);

}
