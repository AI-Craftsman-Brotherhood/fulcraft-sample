# oop-polymorphism

## Overview

A 4-file sample implementing interface-based polymorphism using notification delivery as the subject. With a minimal structure of 1 interface, 2 implementation classes, and 1 service class, it serves as a target for verifying whether FUL can analyze the abstraction boundary of dynamic dispatch and inter-class dependencies.

## Source Structure

| Item | Value |
|------|-----|
| Files | 4 |
| Classes / Interfaces | 4 (`NotificationChannel`, `EmailChannel`, `SmsChannel`, `NotificationService`) |
| Public Methods | 5 (`deliver` x2, `switchChannel`, `sendWelcome`, 1 constructor) |
| Total Lines (including blanks) | 49 |

## Complexity Characteristics

- Baseline for dependency analysis with a minimal inheritance hierarchy (1 interface, 2 implementations)
- `NotificationService` holds and switches dependencies via both constructor injection and `switchChannel()`
- Guard clauses for null / blank checks in `sendWelcome()`
- Implementation classes (`EmailChannel`, `SmsChannel`) have a flat method structure with no branching
- Very low cyclomatic complexity across all classes (useful as a baseline sample)

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Type resolution for the 2 classes implementing `NotificationChannel`. Verifying consistency of the dependency graph across `--engine` options (composite / javaparser / spoon) |
| DOCUMENT | JavaDoc generation for the abstract method `deliver` on the interface and each implementation. Quality of descriptions for return value formats (`"EMAIL:..."` / `"SMS:..."`) |
| REPORT   | Verifying quality gate results for a low-complexity codebase. Confirming that the `--max-cyclomatic` threshold is not triggered |
