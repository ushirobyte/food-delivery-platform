package com.ushirobyte.food.auth_service.controller;

import com.ushirobyte.food.auth_service.dto.ApiResponse;
import com.ushirobyte.food.auth_service.dto.UserDto;
import com.ushirobyte.food.auth_service.mapper.UserMapper;
import com.ushirobyte.food.auth_service.model.User;
import com.ushirobyte.food.auth_service.repository.UserRepository;
import com.ushirobyte.food.auth_service.service.AuthService;
import com.ushirobyte.food.auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    /*@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }*/

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/promote/{id}")
    public ResponseEntity<Void> promote(@PathVariable Long id) {
        userService.promoteToAdmin(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/demote/{id}")
    public ResponseEntity<Void> demote(@PathVariable Long id) {
        userService.demoteToUser(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<Page<UserDto>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.findAllUsers(pageable));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserDto>> getCurrentUser(Authentication auth) {
        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDto userDto = UserMapper.toDto(user);

        return ResponseEntity.ok(
                ApiResponse.<UserDto>builder()
                        .data(userDto)
                        .message("Профиль успешно получен")
                        .timestamp(LocalDate.now())
                        .build()
        );
    }

}
