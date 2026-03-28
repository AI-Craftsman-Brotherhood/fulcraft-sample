# oop-generics

## Overview

A sample of a general-purpose in-memory repository using bounded type generics. Using this as an analysis target for FUL, you can verify how static analysis and documentation tools process a class definition containing a type parameter (`T extends Identifiable`), interface type bounds, and collection operations using generics.

## Source Structure

| Item | Value |
|------|-------|
| Files | 3 |
| Classes / Interfaces | 3 (interface 1, class 2) |
| Public Methods | 5 (`id`, `save`, `findById`, `size`, `name`) |
| Total Lines (including blanks) | 52 |

## Complexity Characteristics

- `MemoryRepository<T extends Identifiable>` is a class with a type parameter, making it a verification point for how JavaParser / Spoon extracts type bounds from the AST
- `MemoryRepository.save` has a compound condition guard clause: `entity == null || entity.id() == null || entity.id().isBlank()`
- `findById` only delegates to `HashMap.get` with no branches, and `size` only returns a single line — both useful for verifying the scoring baseline for low-complexity methods
- `Identifiable` is a minimal interface with only one method, serving as the simplest form of type bound definition subject to analysis
- Inter-class dependencies run in two directions — `Product` → `Identifiable` and `MemoryRepository` → `Identifiable` — with no direct dependency between `Product` and `MemoryRepository`

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Detection accuracy of generics type parameters and type bounds (`T extends Identifiable`). Analysis of field types containing type arguments such as `HashMap<String, T>` |
| DOCUMENT | Quality of JavaDoc generation for type parameters of generic classes (`@param <T>` description). Accuracy of role descriptions for type bounds |
| REPORT   | Complexity score distribution including simple implementation methods (`findById`, `size`). Verification of whether generic structures affect quality gate evaluation |
