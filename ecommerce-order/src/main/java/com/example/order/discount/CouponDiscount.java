package com.example.order.discount;

import com.example.order.model.Money;
import com.example.order.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Coupon-based discount with multiple eligibility conditions.
 * This class intentionally has high cyclomatic complexity to exercise
 * FUL's complexity analysis and quality gate features.
 */
public final class CouponDiscount implements DiscountPolicy {

    private final String couponCode;
    private final BigDecimal discountRate;
    private final Money maxDiscountAmount;
    private final Money minimumOrderAmount;
    private final LocalDate validFrom;
    private final LocalDate validUntil;
    private final int minimumItemCount;

    public CouponDiscount(String couponCode,
                          BigDecimal discountRate,
                          Money maxDiscountAmount,
                          Money minimumOrderAmount,
                          LocalDate validFrom,
                          LocalDate validUntil,
                          int minimumItemCount) {
        if (couponCode == null || couponCode.isBlank()) {
            throw new IllegalArgumentException("Coupon code must not be blank");
        }
        if (discountRate == null || discountRate.signum() <= 0) {
            throw new IllegalArgumentException("Discount rate must be positive");
        }
        if (maxDiscountAmount == null) {
            throw new IllegalArgumentException("Max discount amount must not be null");
        }
        this.couponCode = couponCode;
        this.discountRate = discountRate;
        this.maxDiscountAmount = maxDiscountAmount;
        this.minimumOrderAmount = minimumOrderAmount;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.minimumItemCount = minimumItemCount;
    }

    /**
     * Evaluates eligibility and computes the coupon discount.
     * <p>
     * Eligibility rules (all must pass):
     * <ol>
     *   <li>Current date is within [validFrom, validUntil] if dates are set</li>
     *   <li>Order subtotal meets the minimum amount if configured</li>
     *   <li>Order has at least the minimum item count</li>
     *   <li>Order is not empty</li>
     * </ol>
     * The discount is the lesser of (subtotal * rate) and maxDiscountAmount,
     * and never exceeds the subtotal itself.
     */
    @Override
    public Money calculate(Order order) {
        if (order.isEmpty()) {
            return Money.zero();
        }

        LocalDate today = LocalDate.now();

        if (validFrom != null && today.isBefore(validFrom)) {
            return Money.zero();
        }
        if (validUntil != null && today.isAfter(validUntil)) {
            return Money.zero();
        }

        Money subtotal = order.subtotal();

        if (minimumOrderAmount != null && !subtotal.isGreaterThan(minimumOrderAmount)
                && !subtotal.amount().equals(minimumOrderAmount.amount())) {
            return Money.zero();
        }

        if (order.itemCount() < minimumItemCount) {
            return Money.zero();
        }

        Money calculated = subtotal.multiplyRate(discountRate);

        if (calculated.isGreaterThan(maxDiscountAmount)) {
            calculated = maxDiscountAmount;
        }

        if (calculated.isGreaterThan(subtotal)) {
            return subtotal;
        }

        return calculated;
    }

    @Override
    public String label() {
        return "COUPON:" + couponCode;
    }

    public String couponCode() {
        return couponCode;
    }

    public BigDecimal discountRate() {
        return discountRate;
    }

    public Money maxDiscountAmount() {
        return maxDiscountAmount;
    }
}
