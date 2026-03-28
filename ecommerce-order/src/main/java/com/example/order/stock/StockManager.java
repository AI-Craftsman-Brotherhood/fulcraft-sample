package com.example.order.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages product stock levels and notifies registered observers on change.
 * Acts as the Subject in the Observer pattern.
 */
public final class StockManager {

    private final Map<String, Integer> stockLevels = new HashMap<>();
    private final List<StockObserver> observers = new ArrayList<>();

    public void addObserver(StockObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer must not be null");
        }
        observers.add(observer);
    }

    public void removeObserver(StockObserver observer) {
        observers.remove(observer);
    }

    /**
     * Sets the stock level for a product and fires notifications if the level changed.
     */
    public void setStock(String productCode, int quantity) {
        if (productCode == null || productCode.isBlank()) {
            throw new IllegalArgumentException("Product code must not be blank");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must not be negative");
        }

        int previous = stockLevels.getOrDefault(productCode, 0);
        if (previous == quantity) {
            return;
        }

        stockLevels.put(productCode, quantity);
        notifyObservers(productCode, previous, quantity);
    }

    /**
     * Decreases stock for the given product by the specified amount.
     *
     * @throws IllegalStateException if insufficient stock is available
     */
    public void decreaseStock(String productCode, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        int current = currentStock(productCode);
        if (current < amount) {
            throw new IllegalStateException(
                    "Insufficient stock for " + productCode
                            + ": available=" + current + ", requested=" + amount);
        }
        setStock(productCode, current - amount);
    }

    public int currentStock(String productCode) {
        return stockLevels.getOrDefault(productCode, 0);
    }

    public boolean hasStock(String productCode, int requiredQuantity) {
        return currentStock(productCode) >= requiredQuantity;
    }

    private void notifyObservers(String productCode, int oldQty, int newQty) {
        for (StockObserver observer : observers) {
            observer.onStockChanged(productCode, oldQty, newQty);
        }
    }
}
