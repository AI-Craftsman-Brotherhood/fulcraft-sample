package com.example.order.discount;

import com.example.order.model.Money;
import com.example.order.model.Order;

import java.math.BigDecimal;

/**
 * Applies a percentage-based discount to the order subtotal.
 */
public final class PercentageDiscount implements DiscountPolicy {

    private final BigDecimal rate;

    /**
     * @param ratePercent discount rate in percent (e.g. 10 means 10%)
     */
    public PercentageDiscount(int ratePercent) {
        if (ratePercent <= 0 || ratePercent > 100) {
            throw new IllegalArgumentException(
                    "Rate must be between 1 and 100: " + ratePercent);
        }
        this.rate = BigDecimal.valueOf(ratePercent, 2);
    }

    @Override
    public Money calculate(Order order) {
        return order.subtotal().multiplyRate(rate);
    }

    @Override
    public String label() {
        return rate.movePointRight(2).stripTrailingZeros().toPlainString() + "% OFF";
    }

    public BigDecimal rate() {
        return rate;
    }
}
