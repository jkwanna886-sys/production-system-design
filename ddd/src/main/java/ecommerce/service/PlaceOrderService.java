package ecommerce.service;

import ecommerce.command.PlaceOrderCommand;
import ecommerce.entity.Order;
import ecommerce.repo.OrderRepository;

/**
 * coordinator
 * use case
 */
public class PlaceOrderService {
    private OrderRepository orderRepository;
    private InventoryService inventoryService;
    private PaymentService paymentService;

    public void placeOrder(PlaceOrderCommand cmd) {
        // 1. create domain object
        Order order = Order.create(cmd.getOrderId(), cmd.getItems());
        // 2. reserve inventory (external interaction)
        inventoryService.reserve(cmd.getItems());
        // 3. initiate payment
        paymentService.pay(order.getId());
        // 4. persist
        orderRepository.save(order);
    }
}
