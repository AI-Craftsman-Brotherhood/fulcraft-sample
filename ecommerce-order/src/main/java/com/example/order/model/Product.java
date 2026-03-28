package com.example.order.model;

/**
 * Immutable product definition with a unique code and unit price.
 */
public record Product(String code, String name, Money unitPrice) {

    public Product {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Product code must not be blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name must not be blank");
        }
        if (unitPrice == null) {
            throw new IllegalArgumentException("Unit price must not be null");
        }
    }
}
