package com.ushirobyte.food.auth_service.service;

import com.ushirobyte.food.auth_service.dto.UserDto;
import com.ushirobyte.food.auth_service.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserService {

    public List<User> getUsers();
    public void promoteToAdmin(Long userId);
    public void demoteToUser(Long userId);
    public void deleteUser(Long userId);
    public Page<UserDto> findAllUsers(Pageable pageable);
}
