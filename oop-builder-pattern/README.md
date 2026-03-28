# oop-builder-pattern

## Overview

A sample of the immutable value object creation pattern using a static inner Builder class. Using this as an analysis target for FUL, you can verify how static analysis and documentation tools handle a design that includes nested class structure, method chaining, and constructor validation.

## Source Structure

| Item | Value |
|------|-------|
| Files | 2 |
| Classes / Interfaces | 3 (outer class 1, static nested class 1, service class 1) |
| Public Methods | 9 (`userId`, `displayName`, `email`, `newsletterSubscribed`, `builder`, `displayName(String)`, `email(String)`, `newsletterSubscribed(boolean)`, `build`, `register`) |
| Total Lines (including blanks) | 87 |

## Complexity Characteristics

- `UserProfile` nests a `static final class Builder`, allowing verification of whether FUL can distinguish nested classes from top-level classes
- `if` validation is distributed across the `Builder` constructor, and `null` / `isBlank` guard clauses are spread across each setter method
- The `displayName(String)` setter combines a `null` check and an `isBlank` check with an AND condition, resulting in multiple branches despite shallow nesting depth
- `UserProfileRegistrationService` only uses the `Builder` without any logic of its own, acting as a simple coordinator with few dependent classes
- Inter-class dependencies form a linear chain: `UserProfileRegistrationService` → `UserProfile` → `UserProfile.Builder`

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Detection of nested class (`Builder`) structure and its integration into the dependency graph. Verification of differences in nested class handling between `--engine javaparser` and `spoon` |
| DOCUMENT | Quality of JavaDoc generation for argument descriptions and default values for each `Builder` setter method. Post-condition description for the `build()` method |
| REPORT   | Impact of `final` class and `private` constructor on quality gates. Verification of evaluation score for immutable design |
