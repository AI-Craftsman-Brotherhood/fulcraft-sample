package com.example.order.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Aggregate root representing a customer order.
 * Holds line items and manages state transitions.
 * All mutating operations return a new Order instance to preserve immutability.
 */
public final class Order {

    private final String orderId;
    private final List<OrderItem> items;
    private final OrderStatus status;
    private final Money discountAmount;
    private final Money shippingCost;

    public Order(String orderId) {
        this(orderId, List.of(), OrderStatus.DRAFT, Money.zero(), Money.zero());
    }

    private Order(String orderId,
                  List<OrderItem> items,
                  OrderStatus status,
                  Money discountAmount,
                  Money shippingCost) {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("Order ID must not be blank");
        }
        this.orderId = orderId;
        this.items = List.copyOf(items);
        this.status = status;
        this.discountAmount = discountAmount;
        this.shippingCost = shippingCost;
    }

    // ── Query methods ──

    public String orderId() {
        return orderId;
    }

    public List<OrderItem> items() {
        return items;
    }

    public OrderStatus status() {
        return status;
    }

    public Money discountAmount() {
        return discountAmount;
    }

    public Money shippingCost() {
        return shippingCost;
    }

    /**
     * Sum of all line item subtotals before discounts and shipping.
     */
    public Money subtotal() {
        return items.stream()
                .map(OrderItem::subtotal)
                .reduce(Money.zero(), Money::add);
    }

    /**
     * Final amount: subtotal - discount + shipping.
     * Returns zero if the calculation would yield a negative value.
     */
    public Money total() {
        Money afterDiscount = subtotal().subtract(discountAmount);
        return afterDiscount.add(shippingCost);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int itemCount() {
        return items.stream().mapToInt(OrderItem::quantity).sum();
    }

    // ── State-transition methods (return new Order) ──

    public Order addItem(OrderItem item) {
        requireStatus(OrderStatus.DRAFT, "add items");
        if (item == null) {
            throw new IllegalArgumentException("Item must not be null");
        }
        List<OrderItem> newItems = new ArrayList<>(items);
        newItems.add(item);
        return new Order(orderId, newItems, status, discountAmount, shippingCost);
    }

    public Order applyDiscount(Money discount) {
        requireStatus(OrderStatus.DRAFT, "apply discount");
        if (discount == null) {
            throw new IllegalArgumentException("Discount must not be null");
        }
        if (discount.isGreaterThan(subtotal())) {
            throw new IllegalArgumentException(
                    "Discount exceeds subtotal: " + discount.amount());
        }
        return new Order(orderId, items, status, discount, shippingCost);
    }

    public Order applyShipping(Money shipping) {
        requireStatus(OrderStatus.DRAFT, "apply shipping cost");
        if (shipping == null) {
            throw new IllegalArgumentException("Shipping cost must not be null");
        }
        return new Order(orderId, items, status, discountAmount, shipping);
    }

    public Order confirm() {
        requireStatus(OrderStatus.DRAFT, "confirm");
        if (isEmpty()) {
            throw new IllegalStateException("Cannot confirm an empty order");
        }
        return new Order(orderId, items, OrderStatus.CONFIRMED, discountAmount, shippingCost);
    }

    public Order ship() {
        requireStatus(OrderStatus.CONFIRMED, "ship");
        return new Order(orderId, items, OrderStatus.SHIPPED, discountAmount, shippingCost);
    }

    public Order cancel() {
        if (status == OrderStatus.SHIPPED) {
            throw new IllegalStateException("Cannot cancel a shipped order");
        }
        if (status == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Order is already cancelled");
        }
        return new Order(orderId, items, OrderStatus.CANCELLED, discountAmount, shippingCost);
    }

    // ── Internal helpers ──

    private void requireStatus(OrderStatus expected, String action) {
        if (status != expected) {
            throw new IllegalStateException(
                    "Cannot " + action + " when order is " + status);
        }
    }
}
