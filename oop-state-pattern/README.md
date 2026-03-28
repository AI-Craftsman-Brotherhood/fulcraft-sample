# oop-state-pattern

## Overview

A 5-file sample implementing the State pattern using an order workflow as the subject. It has a structure where 1 interface, 1 Context class, and 3 concrete State classes mutually reference each other, and serves as a target for verifying how FUL analyzes and represents cyclic dependencies (State ↔ Context) and package-private methods within the pattern.

## Source Structure

| Item | Value |
|------|-----|
| Files | 5 |
| Classes / Interfaces | 5 (`OrderState`, `OrderWorkflow`, `DraftOrderState`, `PaidOrderState`, `ShippedOrderState`) |
| Public Methods | 9 (`name` x3, `pay` x3, `ship` x3, `currentState`, `pay`/`ship` on Workflow) |
| Total Lines (including blanks) | 80 |

## Complexity Characteristics

- Bidirectional dependency with mutual references between the Context (`OrderWorkflow`) and State implementation classes
- `transitionTo()` is defined with package-private scope, making access control part of the pattern structure
- Each State class implements disallowed operations (e.g., shipping before payment, operations in final state) as no-ops
- State transitions are expressed as `new XxxOrderState()` instance creation inside State classes (`pay()` / `ship()`)
- Null guard clause in `transitionTo()`
- One-way finite state machine with 3 states (DRAFT → PAID → SHIPPED)

## FUL Feature Coverage

| FUL Feature | Verification Points in This Sample |
|-------------|-----------------------------------|
| ANALYZE  | Detection accuracy of cyclic references between Context and State. Whether the package-private method `transitionTo()` is included in the class dependency graph. Confirming differences between Spoon / JavaParser |
| DOCUMENT | What kind of JavaDoc the LLM generates for no-op implementations (only a `// Final state.` comment). Whether the target state transition class is mentioned |
| REPORT   | Quality gate evaluation against the dependency density across multiple classes. Confirming complexity distribution within the pattern structure |
