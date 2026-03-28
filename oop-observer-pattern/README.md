# oop-observer-pattern

## Overview

A 4-file sample implementing the Observer pattern using inventory management as the subject. It has the typical pattern structure of an interface, a Subject class, and two Observer implementations, and serves as a target for verifying whether FUL can accurately trace the dependency graph between classes and interface implementation relationships.

## Source Structure

| Item | Value |
|------|-----|
| Files | 4 |
| Classes / Interfaces | 4 (`InventoryObserver`, `InventorySubject`, `LowStockObserver`, `StockChangeCounter`) |
| Public Methods | 8 (`onStockChanged` x3, `addObserver`, `removeObserver`, `currentStock`, `setStock`, `lowStockItems`, `updateCount`) |
| Total Lines (including blanks) | 91 |

## Complexity Characteristics

- Dependency graph of 1 interface + 3 classes (one-way notification from Subject to Observer)
- `InventorySubject.setStock()` concentrates multiple guard clauses (null check, blank check, no-change skip), resulting in high branching
- Iteration over multiple observers via a `for` loop
- `LowStockObserver` controls additions and removals to a `Set` through conditional branching
- Internal state protected via defensive copy with `Set.copyOf()`
- Uses `String.isBlank()` (Java 11+)

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Dependency resolution for the 2 classes implementing `InventoryObserver`. Scoring accuracy of the branch count (cyclomatic complexity) in `setStock()` |
| DOCUMENT | JavaDoc generation for the interface method `onStockChanged` and each implementing class. Quality of descriptions for parameter names (`itemCode`, `previousStock`, `currentStock`) |
| REPORT   | Verifying the output format of per-class quality reports for multiple Observer pattern implementation classes |
