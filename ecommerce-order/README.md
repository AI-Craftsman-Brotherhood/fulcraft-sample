# ecommerce-order

## Overview

An 18-file, 5-package sample modelling an e-commerce order placement workflow. The sample combines multiple design patterns (Observer, Strategy, Factory Method, Sealed Hierarchy) and modern Java features (records, sealed interfaces, pattern matching) within a layered package structure. It serves as a comprehensive target for verifying how FUL handles cross-package dependency analysis, varying complexity levels, and full-pipeline (ANALYZE → DOCUMENT → REPORT → EXPLORE) execution on a realistic codebase.

## Source Structure

| Item | Value |
|------|-------|
| Packages | 5 (`model`, `stock`, `discount`, `shipping`, `service`) |
| Files | 18 |
| Classes / Interfaces / Records / Enums | 19 (including 1 nested record `DiscountSummary`) |
| Public Methods | ~50 |
| Total Lines (including blanks) | 1,004 |

### Package Breakdown

| Package | Files | Lines | Role |
|---------|-------|-------|------|
| `model` | 5 | 300 | Domain model layer (records, enum, aggregate root) |
| `stock` | 3 | 147 | Inventory management (Observer pattern) |
| `discount` | 5 | 279 | Discount calculation (Sealed hierarchy + Strategy + pattern matching) |
| `shipping` | 4 | 147 | Shipping cost calculation (Strategy + Factory Method) |
| `service` | 1 | 131 | Orchestration facade (depends on all other packages) |

## Package Dependency Graph

```
service ──→ model       (direct reference)
   │──→ stock       (stock verification)
   │──→ discount    (discount calculation)
   └──→ shipping    (shipping cost)

discount ──→ model      (Money, Order, OrderItem)
shipping ──→ model      (Money, Order)
stock    ──→ model      (no direct reference, uses String product codes)
```

- `model` depends on no other package (bottom layer)
- `service` depends on all packages (top layer)
- `stock`, `discount`, `shipping` depend only on `model` (middle layer)
- No circular dependencies between packages

## Complexity Characteristics

- **High-complexity method**: `CouponDiscount.calculate()` has 8+ branches (date range checks, minimum amount, minimum item count, cap logic) — intentionally designed to trigger FUL quality gate thresholds
- **Aggregate root with guard clauses**: `Order` concentrates state-transition validation (`confirm()`, `ship()`, `cancel()`) with `requireStatus()` helper
- **Facade orchestration**: `OrderService.placeOrder()` chains stock verification → discount → shipping → confirmation → stock decrease in sequence
- **Sealed hierarchy with exhaustive switch**: `DiscountCalculator.toSummary()` uses pattern matching on all 3 `DiscountPolicy` permits
- **Observer notification loop**: `StockManager.setStock()` iterates over observers with guard clauses (null check, blank check, no-change skip)
- **Immutable value object**: `Money` record ensures all arithmetic returns new instances
- **Enum with abstract method**: `OrderStatus.canTransitionTo()` defines per-constant transition rules

## Java Language Features Used

| Feature | Location |
|---------|----------|
| record (Java 16+) | `Money`, `Product`, `OrderItem`, `DiscountSummary` |
| sealed interface (Java 17+) | `DiscountPolicy` |
| pattern matching switch (Java 21+) | `DiscountCalculator.toSummary()` |
| switch expression (Java 14+) | `ShippingMethodFactory.create()` |
| enum with abstract method | `OrderStatus` |
| interface default method | `StockObserver.observerName()` |
| Stream API | `Order.subtotal()`, `Order.itemCount()`, `DiscountCalculator` |
| `List.copyOf()` / `Set.copyOf()` | `Order`, `LowStockAlert` (defensive copy) |
| compact constructor validation | `Money`, `Product`, `OrderItem` |

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE (dependency graph) | Cross-package dependency resolution across 5 packages. Radial dependency from `service` to all others. Confirming no false-positive circular dependency between `discount` ↔ `shipping` |
| ANALYZE (complexity) | Cyclomatic complexity scoring accuracy for `CouponDiscount.calculate()` (expected 8+). Max nesting depth in `OrderService.placeOrder()`. Comparison of complexity distribution across packages |
| ANALYZE (sealed/permits) | Type resolution of `sealed interface DiscountPolicy permits ...` across package. Exhaustiveness check on pattern matching switch |
| ANALYZE (dead code) | Whether `StockObserver.observerName()` default method is flagged when no caller exists in this sample |
| DOCUMENT | JavaDoc generation for records with compact constructor. Quality of descriptions for sealed hierarchy members. Documentation of `OrderService` facade responsibilities and orchestration flow |
| REPORT | Per-package quality metrics comparison. Complexity distribution heatmap across 18 classes. Dependency density analysis between layers |
| EXPLORE | Full pipeline execution on a multi-package project. Interactive exploration of the order placement workflow across package boundaries |
