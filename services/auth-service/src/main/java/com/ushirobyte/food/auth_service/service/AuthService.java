package com.ushirobyte.food.auth_service.service;

import com.ushirobyte.food.auth_service.dto.RegisterRequest;

public interface AuthService {

    public void register(RegisterRequest registerRequest);

}
