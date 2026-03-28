# basic-loops

## Overview

A sample targeting `LoopService`, which consolidates three types of loop structures — `for-each`, `while`, and `do-while` — into a single class. It allows verification of how FUL handles differences in complexity counting per loop syntax and how it treats early-exit flows containing `break` in static analysis.

## Source Structure

| Item | Value |
|------|-------|
| Files | 1 |
| Classes / Interfaces | 1 |
| Public Methods | 3 |
| Total Lines (including blanks) | 45 |

## Complexity Characteristics

- `sum`: simple composition of a `for-each` loop + null guard
- `countPositiveUntilZero`: has 2 branches inside a `while` loop — `break` (end detection) and `if` (positive-value check) — making it the highest complexity of the 3 methods
- `countdownSteps`: a `do-while` loop structure that always executes at least once; edge case behavior for `start <= 0` input is a subject of analysis
- The 3 methods each use a different loop syntax, making this well suited for comparing the consistency of complexity counting across `--engine` options

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Verification of cyclomatic complexity counting differences for `for-each` / `while` / `do-while` loops respectively; control flow tracking of `break` |
| DOCUMENT | Verifying whether the LLM can accurately describe the early-exit condition via `break` and the post-condition of `do-while` in JavaDoc |
| REPORT   | Confirming the behavior of which category `countPositiveUntilZero` is classified into under each of the `--complexity-strategy warn` / `skip` / `split` strategies |
