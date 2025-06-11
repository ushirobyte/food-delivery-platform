package com.ushirobyte.food.auth_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/fun")
@RequiredArgsConstructor
public class FunController {

    private final RestTemplate restTemplate;

    @GetMapping("/quote")
    public ResponseEntity<String> getRandomQuote() {
        Map<?, ?> response = restTemplate.getForObject("https://api.kanye.rest/", Map.class);
        String quote = response != null && response.get("quote") != null ? response.get("quote").toString() : "No quote";
        return ResponseEntity.ok(quote);
    }
}
