# basic-exception

## Overview

A sample targeting `SafeCalculator`, which combines `try-catch-finally` blocks with multiple exception types. It allows verification of whether FUL's complexity measurement and LLM-based document generation can accurately capture exception specifications for an implementation where exception flow branches into multiple paths.

## Source Structure

| Item | Value |
|------|-------|
| Files | 1 |
| Classes / Interfaces | 1 |
| Public Methods | 2 |
| Total Lines (including blanks) | 26 |

## Complexity Characteristics

- `parseAndDivide` has a method call inside a `try` block, two catch clauses (`NumberFormatException` / `IllegalArgumentException`), and a `finally` block
- Exception throwing (`divide`) and exception catching (`parseAndDivide`) are separated within the class, creating a chained control flow between methods
- The `finally` block is a no-op yet represents a boundary case that may be counted toward cyclomatic complexity
- No inheritance, no external dependencies

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Verification of implementation differences in counting each `try-catch-finally` clause as exception flow complexity; comparison of counts across `--engine` options |
| DOCUMENT | Verifying whether the LLM can accurately reflect multiple exception types and the meaning of return values (the distinction between 0 and -1) in `@throws` and `@return` |
| REPORT   | Threshold behavior verification of whether `parseAndDivide` is detected as a split target when `--complexity-strategy split` is applied |
