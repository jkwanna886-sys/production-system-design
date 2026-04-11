package ecommerce.repo;

import ecommerce.entity.Order;

public interface OrderRepository {
    void save(Order order);
    Order findById(Long id);
}
