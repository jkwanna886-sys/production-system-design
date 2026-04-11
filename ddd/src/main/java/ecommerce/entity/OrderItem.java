package ecommerce.entity;

public class OrderItem {
    private Long productId;
    private int quantity;

    public OrderItem(Long productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must > 0");
        }
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
