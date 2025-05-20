package com.ushirobyte.food.auth_service.dto;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class ApiResponse<T> {

    private T data;
    private String message;
    private LocalDate timestamp;

}
