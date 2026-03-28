# oop-default-method

## Overview

A sample of a design pattern that provides common logic via interface `default` methods to minimize the amount of implementation in implementing classes. Using this as an analysis target for FUL, you can verify how static analysis and documentation tools process a three-layer structure consisting of an interface with `default` methods, its implementing classes, and a consuming service.

## Source Structure

| Item | Value |
|------|-------|
| Files | 3 |
| Classes / Interfaces | 3 (interface 1, class 2) |
| Public Methods | 5 (`publish`, `publishUserEvent`, `events`, `login`, `InMemoryEventPublisher.publish`) |
| Total Lines (including blanks) | 48 |

## Complexity Characteristics

- `EventPublisher.publishUserEvent` is a `default` method containing an `if` branch and an early `return`, allowing verification of how control flow inside an interface is handled
- `UserActionService` depends on the `EventPublisher` interface via constructor injection, with no concrete coupling to `InMemoryEventPublisher`
- `InMemoryEventPublisher` only implements `publish` and inherits `publishUserEvent` from `default`, making how the analysis engine handles inherited methods a key verification point
- A defensive copy via `List.copyOf()` is present, including a pattern for preventing leakage of mutable internal state
- The direction of inter-class dependency forms a diamond structure centered on the interface: `UserActionService` → `EventPublisher` ← `InMemoryEventPublisher`

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Detection of `default` methods within interfaces and handling of inherited methods in implementing classes. Interface analysis accuracy with `--engine spoon` |
| DOCUMENT | Quality of JavaDoc generation for `default` methods. Verification of description generation behavior for methods inherited by implementing classes (`InMemoryEventPublisher`) |
| REPORT   | Verification of whether `default` methods inside interfaces are included in complexity calculations. Confirmation of the scope of `--complexity-strategy` application |
