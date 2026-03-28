# simple-java

## Overview

A minimal single-class implementation containing only the four basic arithmetic operations, serving as the baseline reference sample for FUL's happy-path analysis. Both complexity and dependencies are kept to an absolute minimum, allowing verification that the ANALYZE, DOCUMENT, and REPORT features each produce the expected output under the simplest possible conditions.

## Source Structure

| Item | Value |
|------|-------|
| Files | 1 |
| Classes / Interfaces | 1 |
| Public Methods | 4 |
| Total Lines (including blanks) | 27 |

## Complexity Characteristics

- The only branch is a single `if` in the `divide` method (cyclomatic complexity is among the lowest overall)
- Maximum nesting depth of 1
- One exception throw (`ArithmeticException`); no catch / finally
- Zero inter-class dependencies, no inheritance, no interface implementation

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Accuracy of minimum complexity score calculation; confirming consistent output across `--engine` options (composite / javaparser / spoon) |
| DOCUMENT | Quality and completeness of LLM-generated documentation for 4 methods starting from a state with no existing JavaDoc |
| REPORT   | Functions as the passing baseline for the quality gate; useful for verifying `--max-cyclomatic` threshold behavior |
