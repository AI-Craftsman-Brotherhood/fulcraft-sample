package com.example.order.stock;

/**
 * Observer interface notified when inventory levels change.
 */
public interface StockObserver {

    /**
     * Called when stock level of an item changes.
     *
     * @param productCode the product whose stock changed
     * @param oldQuantity stock before the change
     * @param newQuantity stock after the change
     */
    void onStockChanged(String productCode, int oldQuantity, int newQuantity);

    /**
     * Returns a human-readable name for this observer (used in logging).
     * Default implementation returns the simple class name.
     */
    default String observerName() {
        return getClass().getSimpleName();
    }
}
