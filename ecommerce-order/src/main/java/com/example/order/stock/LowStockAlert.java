package com.example.order.stock;

import java.util.HashSet;
import java.util.Set;

/**
 * Observer that tracks products whose stock has fallen below a configurable threshold.
 */
public final class LowStockAlert implements StockObserver {

    private final int threshold;
    private final Set<String> alertedProducts = new HashSet<>();

    public LowStockAlert(int threshold) {
        if (threshold < 0) {
            throw new IllegalArgumentException("Threshold must not be negative");
        }
        this.threshold = threshold;
    }

    @Override
    public void onStockChanged(String productCode, int oldQuantity, int newQuantity) {
        if (newQuantity <= threshold && oldQuantity > threshold) {
            alertedProducts.add(productCode);
        } else if (newQuantity > threshold) {
            alertedProducts.remove(productCode);
        }
    }

    /**
     * Returns an unmodifiable snapshot of currently alerted product codes.
     */
    public Set<String> alertedProducts() {
        return Set.copyOf(alertedProducts);
    }

    public boolean isAlerted(String productCode) {
        return alertedProducts.contains(productCode);
    }

    public int threshold() {
        return threshold;
    }
}
