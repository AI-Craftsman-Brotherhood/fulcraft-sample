package com.example.order.shipping;

import com.example.order.model.Money;
import com.example.order.model.Order;

/**
 * Standard shipping: flat rate, free above a configurable threshold.
 */
public final class StandardShipping implements ShippingMethod {

    private final Money flatRate;
    private final Money freeThreshold;

    public StandardShipping(Money flatRate, Money freeThreshold) {
        if (flatRate == null) {
            throw new IllegalArgumentException("Flat rate must not be null");
        }
        if (freeThreshold == null) {
            throw new IllegalArgumentException("Free threshold must not be null");
        }
        this.flatRate = flatRate;
        this.freeThreshold = freeThreshold;
    }

    @Override
    public Money calculate(Order order) {
        if (order.subtotal().isGreaterThan(freeThreshold)
                || order.subtotal().amount().equals(freeThreshold.amount())) {
            return Money.zero();
        }
        return flatRate;
    }

    @Override
    public String methodName() {
        return "Standard";
    }

    public Money flatRate() {
        return flatRate;
    }

    public Money freeThreshold() {
        return freeThreshold;
    }
}
