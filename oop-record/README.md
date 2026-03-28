# oop-record

## Overview

A sample demonstrating value object design using Java 16 `record`, with an invoice (`Invoice`) and line item (`InvoiceLine`) as the subject. It serves as a target for verifying whether FUL can analyze and generate documentation for `record` declarations distinctly from regular classes, and how it handles the special syntax including compact constructors.

## Source Structure

| Item | Value |
|------|-----|
| Files | 2 |
| Classes / Interfaces | 2 (`Invoice` class, `InvoiceLine` record) |
| Public Methods | 5 (`addLine`, `lineCount`, `totalAmount`, `subtotal`, compact constructor) |
| Total Lines (including blanks) | 45 |

## Complexity Characteristics

- Mixed composition of a `record` declaration (`InvoiceLine`) and a regular class (`Invoice`)
- Multiple `if`-based input validation in the compact constructor of `InvoiceLine` (throws `IllegalArgumentException`)
- List aggregation via `for-each` loop in `Invoice.totalAmount()`
- Null guard clause in `Invoice.addLine()`
- Mix of auto-generated accessors from `record` (`itemName()`, `unitPrice()`, `quantity()`) and an explicitly defined method (`subtotal()`)
- Java 16+ specific language feature (`record` keyword)

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Whether JavaParser / Spoon recognizes the `record` declaration as a type and can enumerate auto-generated accessors as methods. Branch count scoring for the compact constructor |
| DOCUMENT | Quality of auto-generated JavaDoc for `record` components (`itemName`, `unitPrice`, `quantity`). Description of validation rules inside the compact constructor |
| REPORT   | Quality gate evaluation of a codebase containing `record`. Whether the complexity of the constructor with validation is correctly reflected in the report |
