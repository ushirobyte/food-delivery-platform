package com.ushirobyte.food.auth_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class RefreshTokenRequest {
    private String refreshToken;
}
