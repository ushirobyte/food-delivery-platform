package com.ushirobyte.food.auth_service.controller;

import com.ushirobyte.food.auth_service.dto.*;
import com.ushirobyte.food.auth_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return new ResponseEntity<>("Registered Successfully", HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = authService.login(loginRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<String>> refresh(@RequestBody RefreshTokenRequest request) {
        String accessToken = authService.refreshAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .data(accessToken)
                .message("New access token issued")
                .timestamp(LocalDate.now())
                .build());
    }

}
