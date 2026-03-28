# basic-if-switch

## Overview

A sample targeting `DecisionService`, which holds both a chained `if` statement for range determination and an arrow-syntax `switch` expression for multi-value mapping in the same class. It allows verification of whether FUL accurately counts complexity and whether the LLM can document the meaning of each branch for code where two types of branching structures coexist.

## Source Structure

| Item | Value |
|------|-------|
| Files | 1 |
| Classes / Interfaces | 1 |
| Public Methods | 2 |
| Total Lines (including blanks) | 29 |

## Complexity Characteristics

- `gradeLabel` has a boundary-value check followed by a 3-level `if` chain, with an early return at each conditional branch
- `shippingZone` uses an arrow-syntax `switch` expression (Java 14+ syntax) bundling multiple labels, preceded by null / blank guards
- The pattern of listing multiple values in a `switch` `case` clause (`"Tokyo", "Kanagawa", ...`) is a subject for examining how Spoon / JavaParser parse it
- Maximum nesting depth of 1, no external dependencies

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Comparison of cyclomatic complexity counting methods for chained `if` vs. `switch` expressions; observation of `--engine` differences |
| DOCUMENT | Verifying whether the LLM can enumerate return value labels for each branch (`"KANTO"` / `"KANSAI"`, etc.) in JavaDoc |
| REPORT   | Confirming the granularity of quality gate evaluation through a case where the complexity of 2 methods is classified into separate categories |
