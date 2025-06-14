package com.ushirobyte.food.auth_service.service.impl;

import com.ushirobyte.food.auth_service.service.JokeService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class JokeServiceImpl implements JokeService {

    private final List<String> jokes = new ArrayList<>();

    public JokeServiceImpl() throws IOException {
        ClassPathResource resource = new ClassPathResource("jokes.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    jokes.add(line.trim());
                }
            }
        }
    }

    @Override
    public String getRandomJoke() {
        if (jokes.isEmpty()) {
            return "No jokes available";
        }
        int index = ThreadLocalRandom.current().nextInt(jokes.size());
        return jokes.get(index);
    }
}
