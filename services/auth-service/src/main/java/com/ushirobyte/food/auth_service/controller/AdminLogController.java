package com.ushirobyte.food.auth_service.controller;

import com.ushirobyte.food.auth_service.model.ActivityLog;
import com.ushirobyte.food.auth_service.repository.ActivityLogRepository;
import com.ushirobyte.food.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/logs")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminLogController {

    private final ActivityLogRepository activityLogRepository;

    @GetMapping
    public ResponseEntity<List<ActivityLog>> getAllLogs() {
        return ResponseEntity.ok(activityLogRepository.findAll());
    }

}
