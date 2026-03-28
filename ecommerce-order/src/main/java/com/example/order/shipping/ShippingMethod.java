package com.example.order.shipping;

import com.example.order.model.Money;
import com.example.order.model.Order;

/**
 * Strategy interface for computing shipping costs.
 */
public interface ShippingMethod {

    /**
     * Calculates the shipping cost for the given order.
     */
    Money calculate(Order order);

    /**
     * Returns a display name for this shipping method.
     */
    String methodName();
}
