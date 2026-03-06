# Level 4: UseCase Orchestration — Đóng gói quy trình đặt hàng

## 1. Mục tiêu Level này (Objective)

Tạo lớp `OrderPizzaUseCase` để đóng gói toàn bộ quy trình đặt hàng Pizza. Sau level này, `PizzaStoreApplication` chỉ cần gọi `useCase.execute(type)` mà không cần biết bất kỳ chi tiết bên trong nào.

> 💡 **Nguyên lý áp dụng**: Single Responsibility Principle (SRP), Orchestration Logic, Constructor Injection.

---

## 2. Vấn đề cần giải quyết (Problem Statement)

### Trước Level 4 — Code có vấn đề gì?

```java
// PizzaStoreApplication.java (Level 3)
PizzaFactory pizzaFactory = new DefaultPizzaFactory();

Pizza pizza = pizzaFactory.createPizza(PizzaType.CHEESE);
pizza.prepare();  // ← App biết chi tiết quy trình
pizza.bake();     // ← App biết chi tiết quy trình
pizza.cut();      // ← App biết chi tiết quy trình
pizza.box();      // ← App biết chi tiết quy trình
```

**Vấn đề**: `PizzaStoreApplication` vừa phải quản lý Factory, vừa phải biết quy trình xử lý Pizza. Nếu quy trình thay đổi (ví dụ: thêm bước "season" sau "prepare"), bạn phải sửa ngay trong lớp Application.

### Sau Level 4 — Code đã được cải thiện

```java
OrderPizzaUseCase orderPizzaUseCase = new OrderPizzaUseCase(factory);
Pizza pizza = orderPizzaUseCase.execute(PizzaType.CHEESE); // ← Chỉ 1 dòng!
```

---

## 3. Step-by-step triển khai (Implementation)

### Bước 1: Tạo `OrderPizzaUseCase.java`

**Package**: `com.vn.pizzastore.usecase`

```java
public class OrderPizzaUseCase {
    private final PizzaFactory pizzaFactory;

    public OrderPizzaUseCase(PizzaFactory pizzaFactory) {
        this.pizzaFactory = pizzaFactory;
    }

    public Pizza execute(PizzaType pizzaType) {
        Pizza pizza = pizzaFactory.createPizza(pizzaType);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
```

**Phân tích từng dòng code:**

| Dòng                            | Ý nghĩa                                                | Nguyên lý          |
| :------------------------------ | :----------------------------------------------------- | :----------------- |
| `private final PizzaFactory`    | Phụ thuộc vào Interface, không đổi sau khi khởi tạo    | DIP + Immutability |
| Constructor nhận `PizzaFactory` | Constructor Injection — truyền dependency từ bên ngoài | DIP                |
| `execute()` gọi các bước        | Orchestration Logic — chỉ ra lệnh, không tự thực hiện  | SRP                |

### Bước 2: Cập nhật `PizzaStoreApplication.java`

```java
public static void main(String[] args) {
    SpringApplication.run(PizzaStoreApplication.class, args);

    PizzaFactory pizzaFactory = new DefaultPizzaFactory();
    OrderPizzaUseCase orderPizzaUseCase = new OrderPizzaUseCase(pizzaFactory);

    System.out.println("=== ORDERING PIZZAS ===");

    Pizza cheesePizza = orderPizzaUseCase.execute(PizzaType.CHEESE);
    System.out.println("Ordered: " + cheesePizza.getName());
    System.out.println("----------------------------------");

    Pizza greekPizza = orderPizzaUseCase.execute(PizzaType.GREEK);
    System.out.println("Ordered: " + greekPizza.getName());
    System.out.println("----------------------------------");

    Pizza pepperoniPizza = orderPizzaUseCase.execute(PizzaType.PEPPERONI);
    System.out.println("Ordered: " + pepperoniPizza.getName());
}
```

---

## 4. Orchestration Logic — Hiểu sâu hơn

`OrderPizzaUseCase` là **Orchestrator** (Nhạc trưởng):

- **Không tự làm**: Không biết làm bánh, không biết nướng bánh.
- **Biết thứ tự**: Factory tạo → prepare → bake → cut → box.
- **Ủy thác**: Giao cho `PizzaFactory` tạo bánh, giao cho `Pizza` tự xử lý các bước.

```
PizzaStoreApplication
    └── OrderPizzaUseCase (Orchestrator)
            ├── PizzaFactory.createPizza()  → Tạo bánh
            ├── pizza.prepare()             → Chuẩn bị
            ├── pizza.bake()                → Nướng
            ├── pizza.cut()                 → Cắt
            └── pizza.box()                 → Đóng hộp
```

---

## 5. So sánh qua các Level

|                        | Level 2                       | Level 3                  | Level 4                       |
| :--------------------- | :---------------------------- | :----------------------- | :---------------------------- |
| **App biết gì?**       | Biết class cụ thể + quy trình | Biết Factory + quy trình | Chỉ biết `execute(type)`      |
| **Phụ thuộc**          | Concrete class                | Interface (Factory)      | Interface (UseCase + Factory) |
| **Thêm loại Pizza**    | Sửa nhiều nơi                 | Sửa Factory              | Sửa Factory                   |
| **Thay đổi quy trình** | Sửa trong App                 | Sửa trong App            | Sửa trong UseCase             |

---

## 6. Cấu trúc project hoàn chỉnh sau Level 4

```
com.vn.pizzastore/
├── PizzaStoreApplication.java           (Entry Point - Console App)
├── domain/
│   └── pizza/
│       ├── Pizza.java                   (Abstract Class)
│       ├── CheesePizza.java             (Concrete)
│       ├── GreekPizza.java              (Concrete)
│       ├── PepperoniPizza.java          (Concrete)
│       └── PizzaType.java              (Enum)
├── factory/
│   ├── PizzaFactory.java               (Interface)
│   └── DefaultPizzaFactory.java        (Implementation)
└── usecase/
    └── OrderPizzaUseCase.java          (Orchestrator)
```

---

## 7. Kiến thức thu được (Lessons Learned)

1. **Orchestration Logic** — Lớp UseCase chỉ "ra lệnh" chứ không tự thực hiện.
2. **Constructor Injection** — Truyền dependency qua Constructor thay vì `new` bên trong.
3. **`private final`** — Đảm bảo dependency không bị thay đổi sau khi khởi tạo (Immutability).
4. **SRP thực tế**: Mỗi lớp chỉ có 1 lý do để thay đổi:
   - `Pizza` → thay đổi khi recipe thay đổi.
   - `DefaultPizzaFactory` → thay đổi khi thêm/xóa loại Pizza.
   - `OrderPizzaUseCase` → thay đổi khi quy trình đặt hàng thay đổi.
   - `PizzaStoreApplication` → thay đổi khi cách chạy app thay đổi.
5. **Tell, Don't Ask** — App "nói" với UseCase: "Hãy đặt cho tôi một Cheese Pizza" thay vì tự mình làm từng bước.

---

## 8. Bước tiếp theo (What's Next?)

- **Level 5**: Unit Tests — Viết test cho `OrderPizzaUseCase` và `DefaultPizzaFactory`.
- **Level 6**: Factory Method Pattern — Chuyển từ Simple Factory sang Factory Method (mỗi "chi nhánh" tạo Pizza theo phong cách riêng).
