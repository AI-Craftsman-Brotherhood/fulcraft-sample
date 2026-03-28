package com.example.order.discount;

import com.example.order.model.Money;
import com.example.order.model.Order;

import java.util.List;

/**
 * Evaluates a list of discount policies against an order and selects the best one.
 * Uses pattern matching on the sealed {@link DiscountPolicy} hierarchy.
 */
public final class DiscountCalculator {

    private final List<DiscountPolicy> policies;

    public DiscountCalculator(List<DiscountPolicy> policies) {
        if (policies == null) {
            throw new IllegalArgumentException("Policies must not be null");
        }
        this.policies = List.copyOf(policies);
    }

    /**
     * Applies each policy and returns the highest discount amount.
     * If no policies are configured or no discount applies, returns zero.
     */
    public Money bestDiscount(Order order) {
        return policies.stream()
                .map(policy -> policy.calculate(order))
                .reduce(Money.zero(), (a, b) -> a.isGreaterThan(b) ? a : b);
    }

    /**
     * Returns a summary describing each policy's contribution for the given order.
     * Useful for debugging and report generation.
     */
    public List<DiscountSummary> evaluateAll(Order order) {
        return policies.stream()
                .map(policy -> toSummary(policy, order))
                .toList();
    }

    private DiscountSummary toSummary(DiscountPolicy policy, Order order) {
        Money discount = policy.calculate(order);
        String description = switch (policy) {
            case PercentageDiscount p ->
                    "Rate " + p.rate().toPlainString() + " on subtotal " + order.subtotal().amount();
            case FlatDiscount f ->
                    "Flat " + f.amount().amount() + " off";
            case CouponDiscount c ->
                    "Coupon [" + c.couponCode() + "] rate=" + c.discountRate()
                            + " cap=" + c.maxDiscountAmount().amount();
        };
        return new DiscountSummary(policy.label(), discount, description);
    }

    /**
     * Evaluation result for a single policy.
     */
    public record DiscountSummary(String label, Money discount, String description) {}
}
