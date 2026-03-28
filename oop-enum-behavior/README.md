# oop-enum-behavior

## Overview

A sample of a pattern that branches behavior by giving each `enum` constant its own implementation of an abstract method. Using this as an analysis target for FUL, you can verify how static analysis and documentation tools handle a structure containing anonymous class bodies inside `enum`, `abstract` methods, and `switch` expressions.

## Source Structure

| Item | Value |
|------|-------|
| Files | 2 |
| Classes / Interfaces | 2 (enum 1, class 1) |
| Public Methods | 4 (`discountPercent` implementations for 3 constants + `discountedPrice` + `badgeLabel`) |
| Total Lines (including blanks) | 47 |

## Complexity Characteristics

- Each enum constant of `MembershipLevel` (`REGULAR`, `SILVER`, `GOLD`) implements `discountPercent()` in an anonymous class body, resulting in 3 anonymous classes in a single file
- An `abstract` method declaration exists inside the `enum`, making it a verification point for how JavaParser / Spoon converts this into an AST
- `LoyaltyService.badgeLabel` uses a `switch` expression (Java 14+ arrow syntax), resulting in a branch structure that requires exhaustiveness checking
- `LoyaltyService.discountedPrice` has a guard clause with the compound condition `price <= 0 || level == null`
- There is no dependency from `MembershipLevel` to `LoyaltyService`; it is a one-way dependency where `LoyaltyService` references `MembershipLevel`

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Detection of anonymous class bodies inside `enum` and their `abstract` method implementations. Verification of whether the number of branches in `switch` expressions is reflected in complexity scores |
| DOCUMENT | Quality of JavaDoc generation for the behavior of each `enum` constant. Verification of description generation behavior for methods inside anonymous class bodies |
| REPORT   | Verification of the relationship between `switch` expression exhaustiveness and complexity scoring. Use as material for considering appropriate `--max-cyclomatic` threshold values |
