package ecommerce.controller;

import ecommerce.command.PlaceOrderCommand;
import ecommerce.service.PlaceOrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private PlaceOrderService placeOrderService;

    @PostMapping
    public void placeOrder(@RequestBody PlaceOrderCommand cmd) {
        placeOrderService.placeOrder(cmd);
    }
}
