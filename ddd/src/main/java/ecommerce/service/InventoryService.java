package ecommerce.service;

import ecommerce.entity.OrderItem;

import java.util.List;

public interface InventoryService {
    void reserve(List<OrderItem> items);
}
