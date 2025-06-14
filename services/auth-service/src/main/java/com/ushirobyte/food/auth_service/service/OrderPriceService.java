package com.ushirobyte.food.auth_service.service;

import com.ushirobyte.food.auth_service.model.OrderItem;

import java.util.List;

public interface OrderPriceService {
    double calculateTotalPrice(List<OrderItem> items);
}
