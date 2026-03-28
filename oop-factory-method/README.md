# oop-factory-method

## Overview

A sample that uses the Factory Method pattern to switch `ReportFormatter` implementations according to format type. Using this as an analysis target for FUL, you can verify how the dependency graph of a 5-class configuration — where an interface, multiple implementation classes, a static factory class, and a consuming service collaborate — is reproduced, and how interface abstraction affects documentation.

## Source Structure

| Item | Value |
|------|-------|
| Files | 5 |
| Classes / Interfaces | 5 (interface 1, class 4) |
| Public Methods | 5 (`format` × 3, `create`, `createMonthlyReport`) |
| Total Lines (including blanks) | 54 |

## Complexity Characteristics

- `ReportFormatterFactory.create` has an `if ("json".equalsIgnoreCase(format))` branch, representing a simple conditional branch for format type determination
- `ReportFormatterFactory` follows the utility class pattern with a private constructor and only `static` methods, and can be used to verify analysis of instantiation-prohibited design
- Both `JsonReportFormatter` and `TextReportFormatter` implement `format` in a single line with minimum complexity, making them suitable for verifying the lower bound of cyclomatic complexity for implementing classes
- `ReportService` depends only on `ReportFormatterFactory` and the `ReportFormatter` interface, with no direct dependency on concrete classes
- The dependency graph spanning 5 classes (`ReportService` → `ReportFormatterFactory` → `JsonReportFormatter` / `TextReportFormatter`, `ReportService` → `ReportFormatter` ← implementing classes) can be verified for accurate reproduction by FUL's dependency analysis

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Dependency graph generation for implementing class groups with an interface at the top. Analysis of conditional branches using `equalsIgnoreCase`. Verification of multiple engine result integration with `--engine composite` |
| DOCUMENT | Consistency of JavaDoc for the `ReportFormatter` interface and each implementing class. Quality of description generation for the factory method selection logic |
| REPORT   | Confirmation of quality gate evaluation results spanning multiple classes. Visualization of complexity score distribution per implementing class |
