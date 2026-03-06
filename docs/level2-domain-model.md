# Level 2: Domain Model — Xây dựng mô hình Pizza

## 1. Mục tiêu Level này (Objective)

Chuyển đổi yêu cầu nghiệp vụ (Level 1) thành **mã nguồn Java** bằng cách xây dựng lớp trừu tượng `Pizza` và 3 lớp cụ thể (`CheesePizza`, `GreekPizza`, `PepperoniPizza`).

> 💡 **Nguyên lý áp dụng**: Inheritance, Encapsulation, Polymorphism, Abstraction.

---

## 2. Phân tích trước khi code (Analysis Before Coding)

### 2.1 Xác định Shared Behavior vs Variable Behavior

| Hành vi | Loại | Đặt ở đâu? |
| :--- | :--- | :--- |
| `bake()` — Nướng bánh | **Shared** (tất cả Pizza nướng giống nhau) | Concrete method ở lớp cha `Pizza` |
| `cut()` — Cắt bánh | **Shared** | Concrete method ở lớp cha `Pizza` |
| `box()` — Đóng hộp | **Shared** | Concrete method ở lớp cha `Pizza` |
| `prepare()` — Chuẩn bị | **Variable** (mỗi loại có recipe riêng) | Abstract method ở lớp cha `Pizza` |

### 2.2 Xác định Access Modifier

| Thuộc tính | Access Modifier | Lý do |
| :--- | :--- | :--- |
| `name`, `dough`, `sauce`, `toppings` | `protected` | Cho lớp con truy cập trực tiếp trong Constructor |
| `bake()`, `cut()`, `box()` | `public` | Để bên ngoài có thể gọi |
| `prepare()` | `public abstract` | Bắt buộc lớp con phải implement |

---

## 3. Step-by-step triển khai (Implementation)

### Bước 1: Tạo lớp trừu tượng `Pizza.java`

**Package**: `com.vn.pizzastore.domain.pizza`

```java
public abstract class Pizza {
    protected String name;
    protected String dough;
    protected String sauce;
    protected List<String> toppings = new ArrayList<>(); // Tránh NullPointerException

    public abstract void prepare(); // Variable behavior

    // Shared behaviors
    public void bake() { System.out.println("Baking " + name + " for 25 minutes at 350"); }
    public void cut()  { System.out.println("Cutting " + name + " into diagonal slices"); }
    public void box()  { System.out.println("Placing " + name + " in official PizzaStore box"); }

    public String getName() { return name; }
}
```

**Quyết định thiết kế quan trọng:**
- Dùng `protected` thay vì `private` + setter → giữ code gọn, lớp con truy cập trực tiếp.
- Khởi tạo `toppings = new ArrayList<>()` → tránh `NullPointerException` khi gọi `addAll()`.

### Bước 2: Tạo các lớp con cụ thể

**CheesePizza.java:**
```java
public class CheesePizza extends Pizza {
    public CheesePizza() {
        this.name = "The Best Cheese Pizza";
        this.dough = "Very Thin Dough";
        this.sauce = "Very Spicy Sauce";
        this.toppings.addAll(List.of("Cheese", "Mozzarella", "Sausage"));
    }

    @Override
    public void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Tossing dough..." + dough);
        System.out.println("Adding sauce..." + sauce);
        System.out.println("Adding toppings: ");
        toppings.forEach(topping -> System.out.println("\t" + topping));
    }
}
```

**GreekPizza.java** và **PepperoniPizza.java** — cùng cấu trúc, khác recipe.

### Bước 3: Tạo Enum `PizzaType.java`

```java
public enum PizzaType {
    CHEESE, PEPPERONI, GREEK
}
```

**Lý do dùng Enum thay vì String**: Tránh lỗi gõ sai ("chesse" thay vì "cheese"), IDE hỗ trợ auto-complete, dễ mở rộng.

---

## 4. Các lỗi thường gặp và cách xử lý (Common Mistakes)

| Lỗi | Nguyên nhân | Cách fix |
| :--- | :--- | :--- |
| `this.name = "..."` không compile | Field `name` để `private` ở lớp cha | Đổi sang `protected` |
| `toppings.add("A", "B", "C")` lỗi | `add()` chỉ nhận 1 tham số | Dùng `addAll(List.of(...))` |
| `NullPointerException` khi gọi `toppings` | Chưa khởi tạo List | Thêm `= new ArrayList<>()` |
| Lambda in toàn bộ list thay vì từng item | Nhầm tên biến lambda | Dùng `topping` (số ít), không phải `toppings` |

---

## 5. Cấu trúc Hierarchy sau Level 2

```
com.vn.pizzastore.domain.pizza/
├── Pizza.java          (Abstract Class)
├── CheesePizza.java    (Concrete)
├── GreekPizza.java     (Concrete)
├── PepperoniPizza.java (Concrete)
└── PizzaType.java      (Enum)
```

---

## 6. Kiến thức thu được (Lessons Learned)

1. **Phân biệt Shared vs Variable Behavior** trước khi code.
2. **Access Modifier (`protected`)** hỗ trợ kế thừa mà không cần setter.
3. **Khởi tạo Collection trong khai báo** là thói quen tốt để tránh null.
4. **Tên biến trong Lambda** phải khác tên biến bên ngoài để tránh nhầm lẫn.
5. **Enum** an toàn hơn và chuyên nghiệp hơn so với hardcoded String.
6. **`super()` không cần thiết** khi lớp cha chỉ có constructor mặc định (Java tự gọi).
