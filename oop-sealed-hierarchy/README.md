# oop-sealed-hierarchy

## Overview

A 4-file sample implementing a closed type hierarchy using Java 17's `sealed interface` and `permits` clause, with a discount policy as the subject. It serves as a target for verifying whether FUL can analyze Java 17+ specific modifiers such as `sealed` / `final` and accurately document a service class that includes `instanceof` pattern matching.

## Source Structure

| Item | Value |
|------|-----|
| Files | 4 |
| Classes / Interfaces | 4 (`DiscountPolicy` sealed interface, `FlatDiscountPolicy`, `RateDiscountPolicy`, `DiscountService`) |
| Public Methods | 6 (`applyTo` x2, constructors x2, `calculate`, `describe`) |
| Total Lines (including blanks) | 62 |

## Complexity Characteristics

- Closed type hierarchy restricted to 2 implementation classes via `sealed interface` + `permits` clause
- Both `FlatDiscountPolicy` and `RateDiscountPolicy` are `final class`, sealing the inheritance chain
- Percentage range check in the `RateDiscountPolicy` constructor (`IllegalArgumentException`)
- Per-type branching via `instanceof` pattern matching (Java 16+) in `DiscountService.describe()`
- Null / non-positive value guard clauses in `DiscountService.calculate()`
- Java 17+ specific language features (`sealed`, `permits`)

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Accuracy of parsing `sealed interface` and the `permits` clause. Confirming differences in how JavaParser / Spoon represent the sealed type hierarchy. Dependency resolution for `instanceof` pattern matching |
| DOCUMENT | Whether the type constraints of `sealed interface` (`permits FlatDiscountPolicy, RateDiscountPolicy`) are reflected in JavaDoc. Quality of descriptions for the implementation responsibilities of each `final class` |
| REPORT   | Quality gate evaluation of a codebase containing Java 17 features. Confirming branch count scoring for `describe()` |
