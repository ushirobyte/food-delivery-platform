package com.ushirobyte.food.auth_service.service.impl;

import com.ushirobyte.food.auth_service.dto.UserDto;
import com.ushirobyte.food.auth_service.mapper.UserMapper;
import com.ushirobyte.food.auth_service.model.ActivityLog;
import com.ushirobyte.food.auth_service.model.User;
import com.ushirobyte.food.auth_service.repository.ActivityLogRepository;
import com.ushirobyte.food.auth_service.repository.UserRepository;
import com.ushirobyte.food.auth_service.service.UserService;
import com.ushirobyte.food.auth_service.kafka.ActivityProducer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ActivityLogRepository activityLogRepository;
    private final ActivityProducer activityProducer;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void promoteToAdmin(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with id " + userId + " not found"));
        user.setRole("ADMIN");
        String actor = SecurityContextHolder.getContext().getAuthentication().getName();
        logAction(actor, "PROMOTED USER " + userId + " to ADMIN");
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void demoteToUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with id " + userId + " not found"));
        user.setRole("USER");
        String actor = SecurityContextHolder.getContext().getAuthentication().getName();
        logAction(actor, "DEMOTED USER " + userId + " to USER");
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        String actor = SecurityContextHolder.getContext().getAuthentication().getName();
        logAction(actor, "DELETED USER " + userId + " FROM USER");
        userRepository.deleteById(userId);
    }

    @Override
    public Page<UserDto> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(UserMapper::toDto);
    }

    private void logAction(String actorEmail, String action) {
        ActivityLog log = ActivityLog.builder()
                .actorEmail(actorEmail)
                .action(action)
                .timestamp(LocalDateTime.now())
                .build();
        activityLogRepository.save(log);
        activityProducer.send(log);
    }
}
