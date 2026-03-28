package com.example.order.service;

import com.example.order.discount.DiscountCalculator;
import com.example.order.model.Money;
import com.example.order.model.Order;
import com.example.order.model.OrderItem;
import com.example.order.shipping.ShippingMethod;
import com.example.order.stock.StockManager;

import java.util.List;

/**
 * Facade that orchestrates the full order placement workflow:
 * stock verification, discount calculation, shipping, and confirmation.
 *
 * Depends on all four sub-packages (model, stock, discount, shipping),
 * forming a radial dependency graph useful for FUL ANALYZE verification.
 */
public final class OrderService {

    private final StockManager stockManager;
    private final DiscountCalculator discountCalculator;
    private final ShippingMethod shippingMethod;

    public OrderService(StockManager stockManager,
                        DiscountCalculator discountCalculator,
                        ShippingMethod shippingMethod) {
        if (stockManager == null) {
            throw new IllegalArgumentException("StockManager must not be null");
        }
        if (discountCalculator == null) {
            throw new IllegalArgumentException("DiscountCalculator must not be null");
        }
        if (shippingMethod == null) {
            throw new IllegalArgumentException("ShippingMethod must not be null");
        }
        this.stockManager = stockManager;
        this.discountCalculator = discountCalculator;
        this.shippingMethod = shippingMethod;
    }

    /**
     * Places an order by executing the following steps:
     * <ol>
     *   <li>Verify stock availability for every item</li>
     *   <li>Calculate and apply the best discount</li>
     *   <li>Calculate and apply shipping cost</li>
     *   <li>Confirm the order</li>
     *   <li>Decrease stock for each item</li>
     * </ol>
     *
     * @param draft a DRAFT order with at least one item
     * @return the confirmed order with discount and shipping applied
     * @throws IllegalStateException if stock is insufficient
     */
    public Order placeOrder(Order draft) {
        if (draft == null) {
            throw new IllegalArgumentException("Order must not be null");
        }
        if (draft.isEmpty()) {
            throw new IllegalStateException("Cannot place an empty order");
        }

        verifyStock(draft.items());

        Money discount = discountCalculator.bestDiscount(draft);
        Order withDiscount = draft.applyDiscount(discount);

        Money shipping = shippingMethod.calculate(withDiscount);
        Order withShipping = withDiscount.applyShipping(shipping);

        Order confirmed = withShipping.confirm();

        decreaseStock(confirmed.items());

        return confirmed;
    }

    /**
     * Cancels an order and restores stock for all items.
     *
     * @param order the order to cancel (must not be SHIPPED)
     * @return the cancelled order
     */
    public Order cancelOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order must not be null");
        }

        Order cancelled = order.cancel();
        restoreStock(cancelled.items());
        return cancelled;
    }

    /**
     * Returns a breakdown of how each discount policy evaluates the given order.
     * Useful for presenting discount options to the customer.
     */
    public List<?> previewDiscounts(Order draft) {
        return discountCalculator.evaluateAll(draft);
    }

    // ── Stock helpers ──

    private void verifyStock(List<OrderItem> items) {
        for (OrderItem item : items) {
            String code = item.product().code();
            int required = item.quantity();
            if (!stockManager.hasStock(code, required)) {
                throw new IllegalStateException(
                        "Insufficient stock for product " + code
                                + ": available=" + stockManager.currentStock(code)
                                + ", required=" + required);
            }
        }
    }

    private void decreaseStock(List<OrderItem> items) {
        for (OrderItem item : items) {
            stockManager.decreaseStock(item.product().code(), item.quantity());
        }
    }

    private void restoreStock(List<OrderItem> items) {
        for (OrderItem item : items) {
            String code = item.product().code();
            int current = stockManager.currentStock(code);
            stockManager.setStock(code, current + item.quantity());
        }
    }
}
