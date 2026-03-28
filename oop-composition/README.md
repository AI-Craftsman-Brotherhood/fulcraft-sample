# oop-composition

## Overview

A sample of order and line item design demonstrating an aggregate root via composition. Using this as an analysis target for FUL, you can verify how static analysis and documentation tools capture the ownership relationship between classes (`Order` holding a list of `OrderLine`) and the distribution of responsibilities within the aggregate.

## Source Structure

| Item | Value |
|------|-------|
| Files | 2 |
| Classes / Interfaces | 2 (class 2) |
| Public Methods | 6 (`addLine`, `lineCount`, `subtotal`, `containsProduct`, `productName`, `lineTotal`) |
| Total Lines (including blanks) | 62 |

## Complexity Characteristics

- `Order.subtotal` and `Order.containsProduct` each contain a `for` loop, creating inter-class dependencies through `OrderLine` method calls inside the loop
- Guard clauses are distributed across multiple methods, including a `null` check in `addLine` and the `unitPrice <= 0 || quantity <= 0` condition in `lineTotal`
- `Order` holds `List<OrderLine>` as a field but does not expose it directly to the outside, allowing access only through operation methods — suitable for verifying encapsulation strength
- The flattest possible dependency structure with no inheritance hierarchy and no interface implementation makes it easy to use as a baseline for FUL analysis results
- `OrderLine` does not reference `Order`, so the direction of dependency is unified in one direction

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Reflection of ownership relationship between classes (`Order` → `List<OrderLine>`) in the dependency graph. Accuracy of type analysis for collection fields |
| DOCUMENT | Quality of responsibility description for the aggregate root `Order` and its relationship with `OrderLine`. Process flow description generation for methods containing loop processing |
| REPORT   | Baseline value confirmation for cyclomatic complexity scores of methods containing loop branches. Verification of quality gate pass criteria for simple structures |
