# Pizza Store — Lộ trình học từ Basic đến Advanced

Tài liệu này là bản đồ tổng quan giúp bạn theo dõi toàn bộ hành trình phát triển dự án Pizza Store từ Level 1 (cơ bản) đến Level 4 (nâng cao), và định hướng các Level tiếp theo.

---

## Tổng quan các Level

| Level | Tên                    | Chủ đề chính                 | Nguyên lý OOP/SOLID                                   | File tài liệu                                                        |
| :---: | :--------------------- | :--------------------------- | :---------------------------------------------------- | :------------------------------------------------------------------- |
|   1   | Requirement Analysis   | Phân tích yêu cầu nghiệp vụ  | —                                                     | [level1-requirment-analysis.md](./level1-requirment-analysis.md)     |
|   2   | Domain Model           | Xây dựng lớp Pizza + kế thừa | Inheritance, Encapsulation, Polymorphism, Abstraction | [level2-domain-model.md](./level2-domain-model.md)                   |
|   3   | Factory Pattern        | Tách biệt logic tạo object   | OCP, DIP                                              | [level3-factory-pattern.md](./level3-factory-pattern.md)             |
|   4   | UseCase Orchestration  | Đóng gói quy trình đặt hàng  | SRP, Orchestration Logic                              | [level4-usecase-orchestration.md](./level4-usecase-orchestration.md) |
|   5   | Unit Tests             | _(Chưa triển khai)_          | Testing Pyramid                                       | —                                                                    |
|   6   | Factory Method Pattern | _(Chưa triển khai)_          | OCP nâng cao                                          | —                                                                    |

---

## Tiến trình phát triển kiến trúc qua từng Level

### Level 1 → Level 2: Từ yêu cầu đến code

```
[Yêu cầu nghiệp vụ] → [Pizza Abstract Class] → [Concrete Classes]
```

**Học được**: Cách chuyển đổi requirement sang OOP, phân biệt Shared vs Variable behavior.

### Level 2 → Level 3: Từ `new` trực tiếp đến Factory

```
new CheesePizza()  →  factory.createPizza(PizzaType.CHEESE)
```

**Học được**: Tách biệt logic tạo object, Interface trước Implementation sau.

### Level 3 → Level 4: Từ biết chi tiết đến chỉ cần `execute()`

```
prepare + bake + cut + box  →  useCase.execute(type)
```

**Học được**: Orchestration Logic, Constructor Injection, Tell Don't Ask.

---

## Sơ đồ kiến trúc cuối cùng (Level 4)

```
                    PizzaStoreApplication (Console App)
                            │
                            ▼
                    OrderPizzaUseCase (usecase/)
                     │              │
                     ▼              ▼
              PizzaFactory     Pizza.prepare()
              (factory/)       Pizza.bake()
                     │         Pizza.cut()
                     ▼         Pizza.box()
           DefaultPizzaFactory
            │      │       │
            ▼      ▼       ▼
         Cheese  Greek  Pepperoni
              (domain.pizza/)
```

---

## Tài liệu hỗ trợ

| Tài liệu                                                                     | Mô tả                                 |
| :--------------------------------------------------------------------------- | :------------------------------------ |
| [class-responsibility-sheets.md](./class-responsibility-sheets.md)           | Bảng trách nhiệm của từng class (CRC) |
| [technical-terms-glossary.md](./technical-terms-glossary.md)                 | Sổ tay thuật ngữ kỹ thuật             |
| [level2-design-PizzaStore.drawio.png](./level2-design-PizzaStore.drawio.png) | Sơ đồ lớp (Class Diagram)             |

---

## Tổng kết kiến thức thu được

### OOP Fundamentals

- ✅ Inheritance (Kế thừa)
- ✅ Encapsulation (Đóng gói)
- ✅ Polymorphism (Đa hình)
- ✅ Abstraction (Trừu tượng)

### SOLID Principles

- ✅ **S**RP — Mỗi class chỉ có 1 trách nhiệm
- ✅ **O**CP — Mở rộng mà không sửa code cũ
- ✅ **D**IP — Phụ thuộc vào Interface, không phụ thuộc Implementation

### Design Patterns

- ✅ Simple Factory Pattern
- ✅ UseCase / Interactor Pattern

### Clean Code Practices

- ✅ Constructor Injection
- ✅ Immutability (`private final`)
- ✅ Fail Fast (ném exception thay vì trả null)
- ✅ Enum thay thế hardcoded String
