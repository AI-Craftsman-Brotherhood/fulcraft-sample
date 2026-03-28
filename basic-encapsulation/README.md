# basic-encapsulation

## Overview

A sample targeting the `BankAccount` class, which restricts access to private fields exclusively through the constructor and methods. It allows verification of how FUL analyzes and documents a design where input validation, state mutation, and operation result notification via boolean return values coexist.

## Source Structure

| Item | Value |
|------|-------|
| Files | 1 |
| Classes / Interfaces | 1 |
| Public Methods | 4 |
| Total Lines (including blanks) | 45 |

## Complexity Characteristics

- Null / range-check branches are distributed across the constructor and each method, creating a pattern where the aggregated class-level complexity exceeds any single method's value
- `withdraw` has a two-stage condition check (positive-value check → balance check) and returns a boolean
- `final` fields and a mutable field (`balance`) coexist, making this a target for Spoon's state-change analysis
- Zero external class dependencies, no inheritance

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Identification of `final` vs. mutable fields; accuracy of complexity counting for branches inside the constructor |
| DOCUMENT | Verifying whether the LLM can explicitly distinguish in JavaDoc between methods with side effects (balance mutation) and those without (getters) |
| REPORT   | Use case for confirming the difference between per-method and per-class complexity aggregation results in the report |
