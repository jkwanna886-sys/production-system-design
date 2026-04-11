package ecommerce.command;

import ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class PlaceOrderCommand {
    private Long orderId;
    private List<OrderItem> items;
}
