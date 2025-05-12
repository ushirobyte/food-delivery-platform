package com.ushirobyte.food.auth_service.service.impl;

import com.ushirobyte.food.auth_service.dto.RegisterRequest;
import com.ushirobyte.food.auth_service.model.User;
import com.ushirobyte.food.auth_service.repository.UserRepository;
import com.ushirobyte.food.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public void register(RegisterRequest registerRequest) {
        User user = new User();
        user.setFullName(registerRequest.getFullName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        userRepository.save(user);
    }
}
