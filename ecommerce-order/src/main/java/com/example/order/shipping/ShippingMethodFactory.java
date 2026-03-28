package com.example.order.shipping;

import com.example.order.model.Money;

/**
 * Factory for creating shipping method instances by name.
 * Centralises the mapping from user-facing names to concrete strategies.
 */
public final class ShippingMethodFactory {

    private ShippingMethodFactory() {
        // Utility class
    }

    /**
     * Creates a {@link ShippingMethod} for the given method name.
     *
     * @param name shipping method name (case-insensitive): "standard" or "express"
     * @return the corresponding ShippingMethod instance
     * @throws IllegalArgumentException if the name is not recognised
     */
    public static ShippingMethod create(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Shipping method name must not be blank");
        }

        return switch (name.toLowerCase()) {
            case "standard" -> new StandardShipping(
                    Money.of(500),
                    Money.of(5000));
            case "express" -> new ExpressShipping(
                    Money.of(1000),
                    Money.of(200));
            default -> throw new IllegalArgumentException(
                    "Unknown shipping method: " + name);
        };
    }
}
