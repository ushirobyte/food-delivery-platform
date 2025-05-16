package com.ushirobyte.food.auth_service.repository;

import com.ushirobyte.food.auth_service.model.ActivityLog;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
}
