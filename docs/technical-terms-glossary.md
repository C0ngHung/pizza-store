# Technical Terms Glossary - Pizza Store

Tài liệu này lưu trữ và giải thích các thuật ngữ kỹ thuật nâng cao gặp phải trong quá trình phát triển dự án.

---

## 1. Hierarchy (Hệ thống cấp bậc / Kế thừa)

- **Định nghĩa**: Ám chỉ mối quan hệ Cha - Con giữa các lớp (Inheritance).
- **Trong dự án này**: Hierarchy của `Pizza` bao gồm lớp cha `Pizza` và các lớp con `CheesePizza`, `GreekPizza`...
- **Tầm quan trọng**: Giúp tái sử dụng mã nguồn và thiết lập các chuẩn chung cho các đối tượng liên quan.

---

## 2. Factory Method Pattern (Mẫu Phương thức Nhà máy)

### "Factory Method nằm trong hierarchy của PizzaStore" nghĩa là gì?

Đây là một mẫu thiết kế nâng cao hơn so với Simple Factory (chỉ là một lớp tạo object đơn giản). 

**1. Ý tưởng cốt lõi:**
Thay vì thuê một "đơn vị gia công ngoài" (Factory class), tiệm Pizza tự xây dựng một "quy trình chuẩn" (Abstract Store) và để cho mỗi "chi nhánh" (Subclass) tự sản xuất loại bánh đặc thù của họ.

**2. Ví dụ minh họa:**

*   **Lớp Cha (Trình điều khiển quy trình)**: Nắm giữ quy trình `orderPizza` nhưng không biết sẽ tạo ra con Pizza cụ thể nào.
```java
public abstract class PizzaStore {
    // Đây chính là FACTORY METHOD - nằm ngay trong hierarchy của PizzaStore
    protected abstract Pizza createPizza(PizzaType type);

    public Pizza orderPizza(PizzaType type) {
        Pizza pizza = createPizza(type); // Gọi hàm trừu tượng của chính mình

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
}
```

*   **Lớp Con (Người thực hiện việc tạo Object)**: Mỗi lớp con sẽ thực hiện (implement) cái Factory Method đó theo cách riêng của nó.
```java
public class NYPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(PizzaType type) {
        // New York sẽ tạo Pizza kiểu New York (vỏ mỏng, giòn...)
        if (type == PizzaType.CHEESE) return new NYStyleCheesePizza();
        return null;
    }
}

public class ChicagoPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(PizzaType type) {
        // Chicago sẽ tạo Pizza kiểu Chicago (đế dày, rất nhiều phô mai...)
        if (type == PizzaType.CHEESE) return new ChicagoStyleCheesePizza();
        return null;
    }
}
```

**3. So sánh sự khác biệt:**

| Đặc điểm | Simple Factory (Level 3) | Factory Method Pattern (Nằm trong Hierarchy) |
| :--- | :--- | :--- |
| **Vị trí logic tạo Object** | Nằm ở một lớp ngoài (`PizzaFactory`) | Nằm ở các **lớp con** (`NYPizzaStore`, `ChicagoPizzaStore`) |
| **Cách dùng** | `store` gọi `factory.create()` | `store` tự gọi hàm `create()` của chính nó |
| **Tính linh hoạt** | Gom logic tạo object vào 1 chỗ. | Cho phép các chi nhánh (subclasses) tự quyết định "đặc sản" của mình. |

---

## 3. Encapsulation (Tính đóng gói)

- **Định nghĩa**: Che giấu các chi tiết thực thi bên trong và chỉ để lộ ra những gì cần thiết thông qua các phương thức công khai.
- **Áp dụng**: Việc sử dụng `protected` cho các fiel trong lớp `Pizza` thay vì `public` giúp bảo vệ dữ liệu nhưng vẫn cho phép các lớp con tùy chỉnh.

---

## 4. Polymorphism (Tính đa hình)

- **Định nghĩa**: Một đối tượng có thể đóng nhiều vai trò khác nhau hoặc thực hiện các hành vi khác nhau tùy thuộc vào dạng thực tế của nó.
- **Áp dụng**: Hàm `runPizzaFlow(Pizza pizza)` có thể nhận vào bất kỳ loại Pizza nào (`Cheese`, `Greek`, `Pepperoni`) và tự động gọi đúng hàm `prepare()` của loại bánh đó.

---

## 5. Shared Behavior vs Variable Behavior (Hành vi chung vs Hành vi biến đổi)

> 💡 **Đây là khái niệm nền tảng giúp bạn biết "nên đặt code ở đâu".**

### Shared Behavior (Hành vi chung)
- **Là gì?**: Những hành động mà **mọi đối tượng** trong cùng một nhóm đều thực hiện **theo cùng một cách**.
- **Đặt ở đâu?**: Viết thẳng vào phương thức cụ thể (concrete method) của lớp cha.
- **Ví dụ trong dự án**: Mọi loại Pizza đều được nướng (`bake`), cắt (`cut`), đóng hộp (`box`) theo cùng một quy trình. Nên 3 hàm này được viết trong lớp cha `Pizza`.
```java
// Shared behavior - viết 1 lần ở lớp cha, tất cả con đều dùng chung
public void bake() {
    System.out.println("Baking " + name + " for 25 minutes at 350");
}
```

### Variable Behavior (Hành vi biến đổi)
- **Là gì?**: Những hành động mà **mỗi đối tượng** thực hiện **khác nhau** tùy theo loại.
- **Đặt ở đâu?**: Khai báo là `abstract` trong lớp cha, để các lớp con tự hiện thực.
- **Ví dụ trong dự án**: Mỗi loại Pizza `prepare` (chuẩn bị) theo công thức riêng. `CheesePizza` dùng Mozzarella, `GreekPizza` dùng Feta Cheese.
```java
// Variable behavior - bắt buộc mỗi lớp con phải tự define cách của mình
public abstract void prepare();
```

### Tóm gọn quy tắc áp dụng:
| Câu hỏi | Trả lời | Hành động |
| :--- | :--- | :--- |
| Tất cả các lớp con có làm giống nhau không? | **Có** | → Shared Behavior: Viết vào lớp cha |
| Mỗi lớp con làm theo cách riêng không? | **Có** | → Variable Behavior: Khai báo `abstract` |

---

## 6. Factory (Nhà máy / Xưởng sản xuất)

> 💡 **Hiểu đơn giản nhất: Factory là bất cứ thứ gì chịu trách nhiệm tạo ra object.**

### Factory là gì trong lập trình?
Trong cuộc sống thực, một nhà máy nhận đơn đặt hàng và sản xuất ra sản phẩm. Trong lập trình, Factory cũng tương tự: nó nhận một "yêu cầu" (thường là một Type hay String) và trả về đúng Object tương ứng.

**Vấn đề Factory giải quyết:** Thay vì để code phải biết chính xác tên của từng class cụ thể (`new CheesePizza()`, `new GreekPizza()`...), bạn chỉ cần nói "cho tôi một cái CHEESE" và Factory sẽ tự lo.

### Các biến thể phổ biến của Factory:

| Biến thể | Mô tả | Ví dụ trong dự án |
| :--- | :--- | :--- |
| **Simple Factory** | Một lớp đơn giản với hàm `create()` | `DefaultPizzaFactory.createPizza(type)` |
| **Factory Method** | Abstract method trong lớp cha, lớp con override | `abstract Pizza createPizza(type)` trong `PizzaStore` |
| **Abstract Factory** | Tạo ra cả "gia đình" object liên quan | (Cấp độ cao hơn, ví dụ: `NYIngredientFactory`) |

---

## 7. Hierarchy & Demonstrate (Minh họa bằng cây kế thừa)

### Hierarchy trong dự án hiện tại:
```
Pizza (Abstract - Lớp cha chung)
├── CheesePizza (Concrete - Phô mai)
├── GreekPizza  (Concrete - Hy Lạp)
└── PepperoniPizza (Concrete - Xúc xích)

PizzaFactory (Interface - Hợp đồng tạo Pizza)
└── DefaultPizzaFactory (Concrete - Hiện thực mặc định)
```

### Hierarchy trong Factory Method Pattern (Cấp độ nâng cao):
```
PizzaStore (Abstract - Quy trình chuẩn)
├── NYPizzaStore    → override createPizza() → tạo NYStyleCheesePizza...
└── ChicagoPizzaStore → override createPizza() → tạo ChicagoStyleCheesePizza...
```

### Demonstrate (Minh họa luồng thực thi):
Khi bạn gọi `orderPizza(PizzaType.CHEESE)` trong `NYPizzaStore`:
1.  Hàm `orderPizza` của lớp cha `PizzaStore` được gọi.
2.  Bên trong, nó gọi `createPizza(CHEESE)` - nhưng hàm này là `abstract`!
3.  Java tự động gọi đúng phiên bản của lớp con `NYPizzaStore.createPizza()`.
4.  Kết quả: bạn nhận được `NYStyleCheesePizza` mà không cần thay đổi code lớp cha.

> 🔑 **Nguyên lý đằng sau**: Đây chính là **Polymorphism** (Tính đa hình) và **Open/Closed Principle** (Mở để mở rộng, đóng để sửa đổi).

---

## 8. Orchestration Logic (Logic điều phối)

> 💡 **Định nghĩa đơn giản**: Orchestration Logic là đoạn code **chỉ làm nhiệm vụ ra lệnh cho các bước khác thực hiện theo đúng thứ tự**, giống như một nhạc trưởng (Conductor) chỉ huy dàn nhạc.

### Orchestration Logic là gì?

Một lớp "Orchestrator" không tự thực hiện công việc cụ thể — nó **điều phối** (orchestrate) các thành phần khác làm việc theo đúng thứ tự và quy trình.

**Ví dụ thực tế**: Khi bạn đặt hàng online, hệ thống sẽ:
1. Kiểm tra tồn kho (gọi `InventoryService`)
2. Thanh toán (gọi `PaymentService`)
3. Giao hàng (gọi `ShippingService`)
4. Gửi email xác nhận (gọi `NotificationService`)

Đoạn code điều phối 4 bước trên **chính là Orchestration Logic**. Nó không tự kiểm tra tồn kho, không tự thanh toán — nó chỉ biết "bước nào gọi ai, gọi theo thứ tự nào".

### Trong dự án Pizza Store:

`OrderPizzaUseCase` chính là Orchestrator:
```java
public Pizza execute(PizzaType type) {
    Pizza pizza = pizzaFactory.createPizza(type); // Bước 1: Giao cho Factory tạo bánh

    pizza.prepare(); // Bước 2: Bánh tự chuẩn bị
    pizza.bake();    // Bước 3: Bánh tự nướng
    pizza.cut();     // Bước 4: Bánh tự cắt
    pizza.box();     // Bước 5: Bánh tự đóng hộp

    return pizza;    // Bước 6: Trả kết quả
}
```
`OrderPizzaUseCase` không **biết làm bánh** — nó chỉ **biết ra lệnh** cho các bước theo đúng thứ tự.

### Các đặc điểm của Orchestrator tốt:

| Đặc điểm | Giải thích |
| :--- | :--- |
| **Không tự làm** | Ủy thác công việc cho các lớp chuyên biệt |
| **Biết thứ tự** | Quyết định bước nào đến trước, bước nào đến sau |
| **Dễ đọc** | Code như một "kế hoạch" ngắn gọn, không có logic phức tạp |
| **Dễ thay đổi** | Muốn đổi thứ tự? Chỉ cần sửa ở một chỗ duy nhất |

### Đối lập với: Business Logic (Logic nghiệp vụ)

| | Orchestration Logic | Business Logic |
| :--- | :--- | :--- |
| **Làm gì?** | Ra lệnh và điều phối | Thực hiện tính toán, xử lý cụ thể |
| **Ví dụ** | `execute()` trong UseCase | `createPizza()` trong Factory |
| **Ở đâu?** | UseCase / Service | Domain Model / Helper |

---

## 9. Domain Model vs Application Logic (Mô hình nghiệp vụ vs Logic ứng dụng)

> 💡 **Đây là nguyên tắc giúp bạn quyết định "class này thuộc package nào".**

### Domain Model là gì?
- Là những thứ thuộc về **"thế giới thực"** của bài toán.
- Trả lời câu hỏi: **"Bài toán kinh doanh này nói về cái gì?"**
- **Ví dụ**: Pizza là gì, có công thức gì, có những loại nào.

### Application Logic là gì?
- Là những thứ thuộc về **"cách ứng dụng hoạt động"** — kỹ thuật triển khai.
- Trả lời câu hỏi: **"Hệ thống xử lý bài toán này như thế nào?"**
- **Ví dụ**: Ai tạo Pizza (Factory), quy trình đặt hàng (UseCase).

### Phân loại trong dự án Pizza Store:

| Class / Package | Thuộc loại | Lý do | Nên đặt ở đâu? |
| :--- | :--- | :--- | :--- |
| `Pizza`, `CheesePizza`... | **Domain Model** | Mô tả thực thể "Pizza" trong thế giới thực | `domain.pizza/` |
| `PizzaType` | **Domain Model** | Phân loại Pizza — thuộc về nghiệp vụ | `domain.pizza/` |
| `PizzaFactory` | **Application Logic** | "Ai tạo Pizza" — kỹ thuật triển khai, không phải nghiệp vụ | `factory/` |
| `OrderPizzaUseCase` | **Application Logic** | "Quy trình đặt hàng" — lớp điều phối ứng dụng | `usecase/` |

### Quy tắc phân biệt nhanh:

| Câu hỏi | Nếu trả lời "Có" → | Package |
| :--- | :--- | :--- |
| Nếu không có phần mềm, thứ này có tồn tại không? (Pizza, công thức...) | **Domain Model** | `domain/` |
| Thứ này chỉ tồn tại vì ứng dụng cần nó? (Factory, UseCase...) | **Application Logic** | `factory/`, `usecase/` |

> 🔑 **Liên hệ với Clean Architecture**: Đây chính là sự phân tách giữa **Entity Layer** (domain) và **Use Case Layer** (application logic) trong kiến trúc của Robert C. Martin.

---

## English Technical Terms

- **Decoupling**: Giảm sự phụ thuộc giữa các thành phần.
- **Abstraction**: Trừu tượng hóa, tập trung vào "làm cái gì" thay vì "làm như thế nào".
- **Interface Segregation**: Chia nhỏ các interface lớn thành các interface nhỏ hơn, cụ thể hơn.
- **Dependency Inversion (DIP)**: Phụ thuộc vào cái trừu tượng (Interface), không phụ thuộc vào cái cụ thể (Implementation).
- **Shared Behavior**: Hành vi chung giữa các lớp con → đặt ở lớp cha (concrete method).
- **Variable Behavior**: Hành vi thay đổi giữa các lớp con → khai báo `abstract` ở lớp cha.
- **Demonstrate**: Minh họa, mô phỏng cách hoạt động thực tế.
- **Orchestration Logic**: Logic điều phối thứ tự các bước, không tự thực hiện công việc cụ thể.
- **Domain Model**: Mô hình nghiệp vụ — những thứ thuộc về "thế giới thực" của bài toán.
- **Application Logic**: Logic ứng dụng — kỹ thuật triển khai, chỉ tồn tại trong phần mềm.
