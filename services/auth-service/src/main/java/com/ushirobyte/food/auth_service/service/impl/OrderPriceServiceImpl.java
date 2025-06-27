package com.ushirobyte.food.auth_service.service.impl;

import com.ushirobyte.food.auth_service.model.OrderItem;
import com.ushirobyte.food.auth_service.service.OrderPriceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderPriceServiceImpl implements OrderPriceService {
    @Override
    public double calculateTotalPrice(List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (OrderItem item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        // BUG: total price should not be divided by number of items
        return total / items.size();
    }
}
