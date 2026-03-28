package com.example.order.shipping;

import com.example.order.model.Money;
import com.example.order.model.Order;

/**
 * Express shipping: base fee plus per-item surcharge.
 */
public final class ExpressShipping implements ShippingMethod {

    private final Money baseFee;
    private final Money perItemSurcharge;

    public ExpressShipping(Money baseFee, Money perItemSurcharge) {
        if (baseFee == null) {
            throw new IllegalArgumentException("Base fee must not be null");
        }
        if (perItemSurcharge == null) {
            throw new IllegalArgumentException("Per-item surcharge must not be null");
        }
        this.baseFee = baseFee;
        this.perItemSurcharge = perItemSurcharge;
    }

    @Override
    public Money calculate(Order order) {
        Money itemCharge = perItemSurcharge.multiply(order.itemCount());
        return baseFee.add(itemCharge);
    }

    @Override
    public String methodName() {
        return "Express";
    }

    public Money baseFee() {
        return baseFee;
    }

    public Money perItemSurcharge() {
        return perItemSurcharge;
    }
}
