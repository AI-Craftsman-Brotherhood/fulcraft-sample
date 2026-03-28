package com.example.order.discount;

import com.example.order.model.Money;
import com.example.order.model.Order;

/**
 * Sealed hierarchy of discount strategies.
 * Each implementation computes a discount amount based on the order.
 */
public sealed interface DiscountPolicy
        permits PercentageDiscount, FlatDiscount, CouponDiscount {

    /**
     * Calculates the discount amount applicable to the given order.
     *
     * @param order the order to evaluate
     * @return the discount amount (never negative)
     */
    Money calculate(Order order);

    /**
     * Returns a short label describing this policy (e.g. "10% OFF", "500 JPY OFF").
     */
    String label();
}
