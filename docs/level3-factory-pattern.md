# Level 3: Factory Pattern — Tách biệt logic tạo Object

## 1. Mục tiêu Level này (Objective)

Áp dụng **Factory Pattern** để tách biệt việc "sử dụng Pizza" khỏi việc "tạo Pizza". Sau level này, không còn lệnh `new CheesePizza()` nằm trong code nghiệp vụ nữa.

> 💡 **Nguyên lý áp dụng**: Open/Closed Principle (OCP), Dependency Inversion Principle (DIP).

---

## 2. Vấn đề cần giải quyết (Problem Statement)

### Trước Level 3 — Code có vấn đề gì?

```java
// PizzaStoreApplication.java (Level 2)
runPizzaFlow(new CheesePizza());    // ← Phụ thuộc trực tiếp vào lớp cụ thể
runPizzaFlow(new GreekPizza());     // ← Phụ thuộc trực tiếp vào lớp cụ thể
runPizzaFlow(new PepperoniPizza()); // ← Phụ thuộc trực tiếp vào lớp cụ thể
```

**Vấn đề**: Nếu thêm loại Pizza mới (ví dụ: `VeggiePizza`), bạn phải sửa nhiều nơi trong code. Điều này vi phạm **Open/Closed Principle** (mở cho mở rộng, đóng cho sửa đổi).

### Sau Level 3 — Code đã được cải thiện

```java
PizzaFactory factory = new DefaultPizzaFactory();
factory.createPizza(PizzaType.CHEESE);  // ← Chỉ cần biết Type, không cần biết Class
```

---

## 3. Step-by-step triển khai (Implementation)

### Bước 1: Tạo Interface `PizzaFactory.java`

**Package**: `com.vn.pizzastore.factory`

```java
public interface PizzaFactory {
    Pizza createPizza(PizzaType pizzaType);
}
```

**Lý do dùng Interface thay vì Class**: Tuân thủ DIP — code nghiệp vụ phụ thuộc vào abstraction (`PizzaFactory`), không phụ thuộc vào implementation cụ thể.

### Bước 2: Tạo Implementation `DefaultPizzaFactory.java`

```java
public class DefaultPizzaFactory implements PizzaFactory {
    @Override
    public Pizza createPizza(PizzaType pizzaType) {
        return switch (pizzaType) {
            case CHEESE -> new CheesePizza();
            case GREEK -> new GreekPizza();
            case PEPPERONI -> new PepperoniPizza();
            default -> throw new IllegalArgumentException("We do not have " + pizzaType);
        };
    }
}
```

**Quyết định thiết kế quan trọng:**

- Dùng **Switch Expression** (Java 14+) cho code ngắn gọn.
- Nhánh `default` ném `IllegalArgumentException` thay vì trả `null` — Fail Fast tốt hơn.

### Bước 3: Refactor `PizzaStoreApplication.java`

```java
// TRƯỚC (Level 2): Phụ thuộc trực tiếp vào concrete class
runPizzaFlow(new CheesePizza());

// SAU (Level 3): Phụ thuộc vào Factory (Interface)
PizzaFactory pizzaFactory = new DefaultPizzaFactory();
runPizzaFlow(pizzaFactory.createPizza(PizzaType.CHEESE));
```

---

## 4. Các lỗi thường gặp và cách xử lý (Common Mistakes)

| Lỗi                         | Nguyên nhân                                 | Cách fix                                      |
| :-------------------------- | :------------------------------------------ | :-------------------------------------------- |
| `default` branch trả `void` | Dùng `System.out.println` mà không `return` | Ném `throw new IllegalArgumentException(...)` |
| Import sai package          | Class nằm khác package                      | Kiểm tra `import` statements                  |

---

## 5. So sánh trước và sau Level 3

| Tiêu chí                        | Trước (Level 2)          | Sau (Level 3)                       |
| :------------------------------ | :----------------------- | :---------------------------------- |
| **Code biết tên class cụ thể?** | Có (`new CheesePizza()`) | Không (chỉ biết `PizzaType.CHEESE`) |
| **Thêm loại Pizza mới?**        | Sửa nhiều nơi            | Chỉ sửa `DefaultPizzaFactory`       |
| **Nguyên lý tuân thủ**          | —                        | OCP, DIP                            |

---

## 6. Cấu trúc project sau Level 3

```
com.vn.pizzastore/
├── domain/
│   └── pizza/
│       ├── Pizza.java
│       ├── CheesePizza.java
│       ├── GreekPizza.java
│       ├── PepperoniPizza.java
│       └── PizzaType.java
└── factory/
    ├── PizzaFactory.java       (Interface)
    └── DefaultPizzaFactory.java (Implementation)
```

---

## 7. Kiến thức thu được (Lessons Learned)

1. **Factory Pattern** tách biệt logic tạo object khỏi logic sử dụng object.
2. **Interface trước, Implementation sau** — Luôn thiết kế "hợp đồng" trước khi viết code cụ thể.
3. **Switch Expression** (Java 14+) gọn hơn switch-case truyền thống.
4. **Fail Fast** — Ném exception khi nhận input không hợp lệ, đừng trả `null`.
5. **DIP**: Code cấp cao (`PizzaStoreApplication`) phụ thuộc vào `PizzaFactory` (interface), không phụ thuộc `DefaultPizzaFactory` (concrete).

---

## 8. Mở rộng: 3 biến thể của Factory

| Biến thể             | Mô tả                                           | Khi nào dùng?                                    |
| :------------------- | :---------------------------------------------- | :----------------------------------------------- |
| **Simple Factory**   | Một class với hàm `create()` (Đã làm ở Level 3) | Khi logic tạo object đơn giản                    |
| **Factory Method**   | Abstract method trong lớp cha, lớp con override | Khi cần nhiều "chi nhánh" tạo object khác nhau   |
| **Abstract Factory** | Tạo cả "gia đình" object liên quan              | Khi cần tạo nhiều loại object liên quan cùng lúc |
