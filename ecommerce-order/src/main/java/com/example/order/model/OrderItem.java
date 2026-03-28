package com.example.order.model;

/**
 * A single line in an order, binding a product to a purchased quantity.
 */
public record OrderItem(Product product, int quantity) {

    public OrderItem {
        if (product == null) {
            throw new IllegalArgumentException("Product must not be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
    }

    /**
     * Returns the subtotal for this line: unit price * quantity.
     */
    public Money subtotal() {
        return product.unitPrice().multiply(quantity);
    }
}
