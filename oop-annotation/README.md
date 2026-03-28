# oop-annotation

## Overview

A sample of audit metadata processing combining custom annotation definition, application, and reflection-based reading. Using this as an analysis target for FUL, you can verify to what extent the tool can statically analyze `@interface` declarations, `@Retention`/`@Target` meta-annotations, and metadata collection logic using the reflection API.

## Source Structure

| Item | Value |
|------|-------|
| Files | 3 |
| Classes / Interfaces | 3 (annotation 1, class 2) |
| Public Methods | 4 (`auditedActions`, `createOrder`, `cancelOrder`, `health`) |
| Total Lines (including blanks) | 54 |

## Complexity Characteristics

- `AuditMetadataReader.auditedActions` contains a `for` loop and an `if` branch, concentrating reflection API calls into a single method
- A custom annotation definition with `@Retention(RetentionPolicy.RUNTIME)` and `@Target(ElementType.METHOD)` exists and is subject to static analysis of annotation declarations
- `null` / `isBlank` guard clauses appear repeatedly in each method of `AuditedOrderService`
- Inter-class dependencies are separated into two directions: `AuditMetadataReader` → `AuditedAction` (annotation type reference) and `AuditedOrderService` → `AuditedAction` (application)
- Reflection API calls (`getDeclaredMethods`, `getAnnotation`) are treated as dynamic calls during static analysis, making analysis differences between engines easy to observe

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Detection of `@interface` declarations and listing of annotated methods. Verification that `--engine spoon` accurately extracts annotation meta-information |
| DOCUMENT | Quality of JavaDoc generation for the annotation definition file (`AuditedAction`). Description generation for metadata reading logic using reflection |
| REPORT   | Complexity scoring for `for` loop + `if` branch combinations. Base case for verifying the `--complexity-strategy` option behavior |
