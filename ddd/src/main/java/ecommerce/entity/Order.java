package ecommerce.entity;

import ecommerce.enums.OrderStatus;

import java.util.List;

public class Order {

    private Long id;
    private List<OrderItem> items;
    private OrderStatus status;

    private Order(Long id, List<OrderItem> items) {
        this.id = id;
        this.items = items;
        this.status = OrderStatus.CREATED;
    }

    public static Order create(Long id, List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must have items");
        }
        return new Order(id, items);
    }

    public void markPaid() {
        if (status != OrderStatus.CREATED) {
            throw new IllegalStateException("Cannot pay");
        }
        this.status = OrderStatus.PAID;
    }

    public void cancel() {
        if (status != OrderStatus.CREATED) {
            throw new IllegalStateException("Cannot cancel");
        }
        this.status = OrderStatus.CANCELLED;
    }

    public Long getId() {
        return id;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
