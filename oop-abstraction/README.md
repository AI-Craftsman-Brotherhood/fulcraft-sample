# oop-abstraction

## Overview

A price calculation sample implementing the Template Method pattern with an abstract class. Using this as an analysis target for FUL, you can verify how the tool detects and documents the inheritance hierarchy between an abstract class and concrete subclasses, method locking via `final`, and extension points via `protected` hook methods.

## Source Structure

| Item | Value |
|------|-------|
| Files | 2 |
| Classes / Interfaces | 2 (abstract class 1, concrete class 1) |
| Public Methods | 3 (`calculateFinalPrice`, `discountAmount`, `minimumPrice`) |
| Total Lines (including blanks) | 43 |

## Complexity Characteristics

- `calculateFinalPrice` contains an `if` branch and a lower-bound check via `Math.max`, concentrating multiple conditional branches within a single method
- The `abstract` method `discountAmount` is implemented in a subclass and is therefore treated as dynamic dispatch during static analysis
- The constructor of `HolidayPriceCalculator` contains range validation (`if` branch + `throw`)
- Inheritance is only one level deep with no circular dependencies; inter-class dependencies are a simple parent-child relationship only
- Multiple access modifiers (`protected` / `final`) coexist, allowing verification of whether Spoon-based analysis can accurately extract modifier combinations

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Detection accuracy of abstract class, `final` methods, and `abstract` methods. Comparison of results between `--engine javaparser` and `spoon` |
| DOCUMENT | Whether LLM can generate comments that supplement the intent of `abstract` method implementations. Quality of role descriptions for `protected` hooks |
| REPORT   | Baseline confirmation of cyclomatic complexity in a simple inheritance structure. Verification of `--max-cyclomatic` threshold behavior |
