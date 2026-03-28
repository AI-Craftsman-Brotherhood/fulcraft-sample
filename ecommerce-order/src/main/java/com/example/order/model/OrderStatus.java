package com.example.order.model;

/**
 * Lifecycle states of an order.
 * Each constant defines which transitions are permitted from that state.
 */
public enum OrderStatus {

    DRAFT {
        @Override
        public boolean canTransitionTo(OrderStatus next) {
            return next == CONFIRMED;
        }
    },

    CONFIRMED {
        @Override
        public boolean canTransitionTo(OrderStatus next) {
            return next == SHIPPED || next == CANCELLED;
        }
    },

    SHIPPED {
        @Override
        public boolean canTransitionTo(OrderStatus next) {
            return false;
        }
    },

    CANCELLED {
        @Override
        public boolean canTransitionTo(OrderStatus next) {
            return false;
        }
    };

    /**
     * Returns {@code true} if transitioning from this state to {@code next} is allowed.
     */
    public abstract boolean canTransitionTo(OrderStatus next);
}
