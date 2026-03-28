# oop-nested-class

## Overview

A single-file sample that contains a static nested class `Slot` inside the `Schedule` class. It serves as a target for verifying whether FUL can detect and analyze multiple type definitions (outer class + nested class) from a single file.

## Source Structure

| Item | Value |
|------|-----|
| Files | 1 |
| Classes / Interfaces | 2 (`Schedule`, `Schedule.Slot`) |
| Public Methods | 5 (`addSlot`, `totalMinutes`, `titles`, `Slot.title`, `Slot.minutes`) |
| Total Lines (including blanks) | 49 |

## Complexity Characteristics

- Two-layer structure where an outer class and a static nested class coexist in a single file
- The nested class `Slot` performs defensive normalization in its constructor: `null` check and rejection of negative values
- List traversal via `for-each` loops in `totalMinutes()` / `titles()`
- Immutable value object (`Slot`) composed entirely of `final` fields
- Inter-class dependency: one-way dependency where `Schedule` creates and aggregates `Slot`

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Whether the nested class is detected as an independent type. Confirming differences in how JavaParser / Spoon each represent the nested structure |
| DOCUMENT | Whether JavaDoc is generated individually for `Schedule` and `Schedule.Slot`. Whether the nesting relationship is reflected in the documentation |
| REPORT   | Baseline quality score confirmation as a single-file, low-complexity sample |
