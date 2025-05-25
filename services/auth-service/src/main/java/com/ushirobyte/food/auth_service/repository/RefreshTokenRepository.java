package com.ushirobyte.food.auth_service.repository;

import com.ushirobyte.food.auth_service.model.RefreshToken;
import com.ushirobyte.food.auth_service.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(User user);

}
