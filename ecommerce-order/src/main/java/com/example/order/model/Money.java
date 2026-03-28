package com.example.order.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Immutable value object representing a monetary amount.
 * All arithmetic operations return a new Money instance.
 */
public record Money(BigDecimal amount) {

    private static final int SCALE = 2;

    public Money {
        if (amount == null) {
            throw new IllegalArgumentException("Amount must not be null");
        }
        amount = amount.setScale(SCALE, RoundingMode.HALF_UP);
    }

    public static Money of(long value) {
        return new Money(BigDecimal.valueOf(value));
    }

    public static Money of(String value) {
        return new Money(new BigDecimal(value));
    }

    public static Money zero() {
        return new Money(BigDecimal.ZERO);
    }

    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    public Money subtract(Money other) {
        Money result = new Money(this.amount.subtract(other.amount));
        if (result.amount.signum() < 0) {
            throw new IllegalArgumentException(
                    "Resulting amount must not be negative: " + result.amount);
        }
        return result;
    }

    public Money multiply(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must not be negative");
        }
        return new Money(this.amount.multiply(BigDecimal.valueOf(quantity)));
    }

    public Money multiplyRate(BigDecimal rate) {
        if (rate.signum() < 0) {
            throw new IllegalArgumentException("Rate must not be negative");
        }
        return new Money(this.amount.multiply(rate));
    }

    public boolean isGreaterThan(Money other) {
        return this.amount.compareTo(other.amount) > 0;
    }

    public boolean isZero() {
        return this.amount.signum() == 0;
    }
}
