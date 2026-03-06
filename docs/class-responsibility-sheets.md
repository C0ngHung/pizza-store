# Class Responsibility Sheets (CRC) - Pizza Store

Tài liệu này xác định rõ vai trò và trách nhiệm của từng class để đảm bảo tuân thủ nguyên lý SOLID ngay từ đầu.

## 1. ANALYSIS - Phân tích cốt lõi

| Class                   | Concept Nghiệp vụ     | Trách nhiệm chính (SRP)       | Phụ thuộc (Dependencies) | Lý do thay đổi (Change Reason) |
| :---------------------- | :-------------------- | :---------------------------- | :----------------------- | :----------------------------- |
| **Pizza**               | Khái niệm Pizza chung | Quản lý data + behavior chung | Java Collections         | Định nghĩa chung Pizza đổi     |
| **CheesePizza**         | Pizza phô mai cụ thể  | Định nghĩa recipe riêng       | Pizza (Base)             | Công thức Cheese Pizza đổi     |
| **GreekPizza**          | Pizza Hy Lạp cụ thể   | Định nghĩa recipe riêng       | Pizza (Base)             | Công thức Greek Pizza đổi      |
| **PepperoniPizza**      | Pizza xúc xích Ý      | Định nghĩa recipe riêng       | Pizza (Base)             | Công thức Pepperoni đổi        |
| **PizzaType**           | Danh mục loại Pizza   | Cung cấp danh sách type chuẩn | Không có                 | Thêm loại Pizza mới            |
| **PizzaFactory**        | Contract tạo Pizza    | Định nghĩa cách tạo Pizza     | PizzaType, Pizza         | Cách thức tạo Pizza đổi        |
| **DefaultPizzaFactory** | Hiện thực Factory     | Map type sang concrete class  | Pizza classes            | Logic mapping thay đổi         |
| **OrderPizzaUseCase**   | Luồng đặt Pizza       | Điều phối (Orchestration)     | PizzaFactory, Pizza      | Flow đặt hàng thay đổi         |
| **PizzaStoreApp**       | Điểm bắt đầu (Entry)  | Boot app & DI                 | UseCase, Factory         | Cách chạy app thay đổi         |

---

## 2. Chi tiết trách nhiệm (Class Responsibilities)

### 2.1 Pizza (Abstract)

- **Vai trò**: Base class trừu tượng.
- **Trách nhiệm**: Giữ state (name, dough, sauce, toppings), cung cấp behavior mặc định (`bake`, `cut`, `box`), ép buộc `prepare` (abstract).
- **Không được làm**: Logic chọn loại Pizza, logic tạo Object, console input.

### 2.2 Concrete Pizzas (Cheese, Greek, Pepperoni)

- **Vai trò**: Thực thi cụ thể từng loại bánh.
- **Trách nhiệm**: Thiết lập các thuộc tính riêng (recipe) và override `prepare`.
- **Không được làm**: Logic điều phối use case, login factory, logic UI.

### 2.3 PizzaFactory (Interface) & DefaultPizzaFactory

- **Vai trò**: Abstraction cho việc tạo Object (Factory Pattern).
- **Trách nhiệm**: Khởi tạo đúng loại Pizza dựa trên `PizzaType`.
- **Không được làm**: Logic nướng/cắt/đóng hộp, console input.

### 2.4 OrderPizzaUseCase

- **Vai trò**: Interactor / Orchestrator.
- **Trách nhiệm**: Điều phối flow: Factory tạo Pizza -> Prepare -> Bake -> Cut -> Box.
- **Không được làm**: Chứa code recipe chi tiết, khởi tạo `new` concrete class trực tiếp.

---

## 3. Quyết định Thiết kế (Design Decisions)

1. **Pizza là Abstract Class**: Cho phép tái sử dụng code chung (fields, methods) mà vẫn đảm bảo tính trừu tượng.
2. **Method prepare() là Abstract**: Bắt buộc mọi loại Pizza phải có "công thức chuẩn bị" riêng (Polymorphism).
3. **DIP (Dependency Inversion)**: `OrderPizzaUseCase` phụ thuộc vào `PizzaFactory` (Interface), không phụ thuộc vào `DefaultPizzaFactory`.
4. **Enum PizzaType**: Thay thế hard-coded strings, giúp code an toàn và dễ bảo trì.
