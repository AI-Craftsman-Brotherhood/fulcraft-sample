# oop-strategy-pattern

## Overview

A 4-file sample implementing the Strategy pattern using shipping fee calculation as the subject. It includes a Context class that supports both constructor injection and runtime strategy switching (`setShippingStrategy()`), and serves as a target for verifying whether FUL can analyze and document the dependency relationships and branching logic of the strategy interface and its implementation classes.

## Source Structure

| Item | Value |
|------|-----|
| Files | 4 |
| Classes / Interfaces | 4 (`ShippingStrategy`, `StandardShippingStrategy`, `ExpressShippingStrategy`, `CheckoutService`) |
| Public Methods | 6 (`shippingFee` x2, 1 constructor, `setShippingStrategy`, `totalWithShipping`) |
| Total Lines (including blanks) | 56 |

## Complexity Characteristics

- Standard Strategy structure of 1 interface, 2 implementation classes, and 1 Context class
- `StandardShippingStrategy.shippingFee()` has 2 conditional branches (`itemCount <= 0` guard + ternary operator for free shipping judgment)
- `ExpressShippingStrategy.shippingFee()` has 1 conditional branch (`itemCount <= 0` guard) + dynamic fee calculation via arithmetic expression
- `CheckoutService` holds the strategy via both constructor injection and a `setShippingStrategy()` setter
- Non-negative guard via `Math.max(0, ...)` in `totalWithShipping()`
- The algorithms clearly differ between implementation classes (flat fee + per-item charge vs. threshold judgment), making differences in LLM descriptions easy to observe

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Dependency resolution for the 2 classes implementing `ShippingStrategy`. How runtime replacement via the `setShippingStrategy()` setter is represented in static analysis |
| DOCUMENT | Whether the LLM can accurately describe the business rules for arithmetic-expression-based fee calculation (`1200 + (itemCount * 80)`) and threshold-based fee calculation (`subtotal >= 5000 ? 0 : 500`) |
| REPORT   | Comparison of cyclomatic complexity scores for both Strategy classes. Verifying behavior of `--max-cyclomatic` threshold and `--complexity-strategy` option |
