package com.ushirobyte.food.auth_service.service;

import com.ushirobyte.food.auth_service.model.User;

import java.util.List;

public interface UserService {

    public List<User> getUsers();
    public void promoteToAdmin(Long userId);
    public void demoteToUser(Long userId);
    public void deleteUser(Long userId);
}
