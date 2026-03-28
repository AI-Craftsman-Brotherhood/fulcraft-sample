package com.example.order.discount;

import com.example.order.model.Money;
import com.example.order.model.Order;

/**
 * Applies a fixed-amount discount, capped at the order subtotal.
 */
public final class FlatDiscount implements DiscountPolicy {

    private final Money amount;

    public FlatDiscount(Money amount) {
        if (amount == null || amount.isZero()) {
            throw new IllegalArgumentException("Discount amount must be positive");
        }
        this.amount = amount;
    }

    @Override
    public Money calculate(Order order) {
        Money subtotal = order.subtotal();
        if (amount.isGreaterThan(subtotal)) {
            return subtotal;
        }
        return amount;
    }

    @Override
    public String label() {
        return amount.amount().toPlainString() + " OFF";
    }

    public Money amount() {
        return amount;
    }
}
