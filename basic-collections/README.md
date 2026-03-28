# basic-collections

## Overview

A sample targeting `InventoryService`, which accepts `List` and `Map` as input and performs category aggregation, low-stock determination, and total calculation. It allows verification of how FUL's static analysis and document generation behave against an implementation pattern where collection operations and null-guard branches are combined.

## Source Structure

| Item | Value |
|------|-------|
| Files | 1 |
| Classes / Interfaces | 1 |
| Public Methods | 3 |
| Total Lines (including blanks) | 49 |

## Complexity Characteristics

- Each method has a 3-layer structure of null guard + loop + conditional branch, resulting in cyclomatic complexity clearly higher than the baseline
- `continue` / early `return` exit patterns are mixed inside `for-each` loops
- Complex collection operations involving `Map.Entry` iteration and `getOrDefault`
- No inter-class dependencies, no inheritance, only standard library (`java.util`) used

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Complexity score calculation for combined loop + branch patterns; comparison of dependency resolution results between Spoon and JavaParser |
| DOCUMENT | Verifying whether the LLM can accurately describe preconditions and return values for methods with nullable parameters or early returns |
| REPORT   | Threshold behavior verification of whether the sample is classified as medium complexity when `--complexity-strategy warn` is applied |
