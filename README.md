# fulcraft-java-sample

Sample Java projects for each case exported from [FUL](https://github.com/AI-Craftsman-Brotherhood/fulcraft) E2E fixtures.

## Purpose

This repository provides small, focused Java examples used to validate and demonstrate FUL test generation behavior.

## Cases

### basic

| Case | Description |
|------|-------------|
| [simple-java](simple-java/) | Four arithmetic operations and division-by-zero exception |
| [basic-collections](basic-collections/) | HashMap / List operations for inventory management |
| [basic-encapsulation](basic-encapsulation/) | Private final fields and input validation |
| [basic-exception](basic-exception/) | try / catch / finally and exception type differentiation |
| [basic-if-switch](basic-if-switch/) | if-else chains and switch expressions (Java 14+) |
| [basic-inheritance](basic-inheritance/) | Interface implementation and discount rate validation |
| [basic-loops](basic-loops/) | for-each, while with break, do-while patterns |

### oop

| Case | Description |
|------|-------------|
| [oop-abstraction](oop-abstraction/) | Template Method pattern with abstract / final hooks |
| [oop-annotation](oop-annotation/) | Custom annotation definition and runtime reflection |
| [oop-builder-pattern](oop-builder-pattern/) | Nested Builder with required / optional parameter separation |
| [oop-composition](oop-composition/) | Has-a relationship with aggregate root |
| [oop-default-method](oop-default-method/) | Interface default method for shared logic |
| [oop-enum-behavior](oop-enum-behavior/) | Enum with abstract method per constant |
| [oop-factory-method](oop-factory-method/) | Factory Method pattern for formatter selection |
| [oop-generics](oop-generics/) | Bounded type parameter `<T extends Identifiable>` |
| [oop-nested-class](oop-nested-class/) | Static nested class as internal data model |
| [oop-observer-pattern](oop-observer-pattern/) | Observer pattern for inventory change notification |
| [oop-polymorphism](oop-polymorphism/) | Runtime channel switching via interface |
| [oop-record](oop-record/) | Java 16+ record with compact constructor validation |
| [oop-sealed-hierarchy](oop-sealed-hierarchy/) | Sealed interface with pattern matching (Java 17+) |
| [oop-state-pattern](oop-state-pattern/) | State pattern for order workflow transitions |
| [oop-strategy-pattern](oop-strategy-pattern/) | Strategy pattern for shipping cost calculation |

### comprehensive

| Case | Description |
|------|-------------|
| [ecommerce-order](ecommerce-order/) | 18-file, 5-package e-commerce order workflow combining Observer, Strategy, Factory Method, Sealed Hierarchy, records, and pattern matching |

## Usage

Each directory is an independent sample project.
You can compile sources directly with `javac` or import a case directory into your IDE.

## Relationship to FUL

This repository is maintained as a companion sample set for the [FUL](https://github.com/AI-Craftsman-Brotherhood/fulcraft) project.
License and reporting policies follow the main FUL repository.

## License

This project is released under the **FUL License (Proprietary / Source Available)**.
See [LICENSE](LICENSE) for details.

### License Summary
- Personal / student / non-commercial use is permitted.
- Commercial use is prohibited unless explicit written permission is granted.
- Modification and redistribution are prohibited.

For commercial licensing or explicit permissions:
- Email: support@craftsman-bro.com
- Web: https://craftsman-bro.com/en/contact/

## Contributing

Public pull requests are not accepted.
Bug reports are welcome via this repository issue tracker:
- https://github.com/AI-Craftsman-Brotherhood/fulcraft-sample/issues/new

See [CONTRIBUTING.md](CONTRIBUTING.md) for details.

## Security

Please report vulnerabilities through the channels described in [SECURITY.md](SECURITY.md).
